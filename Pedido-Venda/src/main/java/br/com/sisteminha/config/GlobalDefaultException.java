package br.com.sisteminha.config;

import java.io.IOException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.metrics.CounterService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import br.com.sisteminha.util.jsf.BusinessException;

@ControllerAdvice
public class GlobalDefaultException {

	@Autowired
	public Environment env;

	@Autowired
	private CounterService counterService;

	@Autowired
	private ResourceBundleMessageSource messageSource;

	@Bean
	public ResourceBundleMessageSource getMessageSource() {
		final ResourceBundleMessageSource msgSource = new ResourceBundleMessageSource();
		msgSource.setBasename("db_messages");
		return msgSource;
	}

	private final Logger logger = LoggerFactory.getLogger(GlobalDefaultException.class);

	@ExceptionHandler(BusinessException.class)
	ResponseEntity<?> handleBadRequests(final BusinessException e, final HttpServletRequest req)
			throws IOException {
		final Map<String, Object> result = new LinkedHashMap<>();
		final String errorCode = e.getErrorCode().getCodigo();

		counterService.increment("exception.count");
		if (errorCode != null) {
			counterService.increment("exception.count." + errorCode);
		}

		result.put("errorCode", errorCode);

		String msg = env.getProperty("error.msg." + e.getErrorCode());
		if (msg == null) {
			msg = checkDatabaseError(e);
		}
		if (msg == null) {
			msg = e.getMessage();
		}
		if (e.getParameters() != null && !e.getParameters().isEmpty()) {
			if (msg != null) {
				msg = MessageFormat.format(msg, e.getParameters().toArray());
			} else {
				msg = e.getParameters().iterator().next().toString();
			}
		}

		result.put("errorMessage", msg != null ? msg : e.getMessage());
		result.put("remoteAddress", this.getRemoteAddress(req));
		result.put("path", req.getRequestURI());
		result.put("forward", req.getAttribute(RequestDispatcher.FORWARD_REQUEST_URI));
		result.put("textoSuporte", env.getProperty("textoSuporteGenerico"));

		logger.warn(result.toString(), e);

		return ResponseEntity.badRequest().body(result);
	}

	private String checkDatabaseError(final Throwable e) {
		String exceptionMessage = null;
		if (e.getMessage() != null && (e.getMessage().contains("ConstraintViolationException")
				|| e.getClass().getName().contains("ConstraintViolationException"))) {
			exceptionMessage = e.getMessage();
		}

		if (exceptionMessage != null) {
			exceptionMessage = extractConstraint(exceptionMessage);

		}
		if ((exceptionMessage == null || exceptionMessage.equalsIgnoreCase("null")) && e.getCause() != null) {
			return checkDatabaseError(e.getCause());
		}

		if (exceptionMessage != null) {
			return messageSource.getMessage(exceptionMessage, null, null, Locale.getDefault());
		}

		return null;
	}

	private String extractConstraint(String exceptionMessage) {
		if (exceptionMessage == null) {
			return null;
		}

		final int indexOf = exceptionMessage.indexOf("constraint [");
		if (indexOf >= 0 && indexOf + 12 < exceptionMessage.length()) {
			exceptionMessage = exceptionMessage.substring(indexOf + 12);
			exceptionMessage = exceptionMessage.substring(0, exceptionMessage.indexOf("]"));
		} else if (exceptionMessage.indexOf("CONSTRAINT `") >= 0) {
			exceptionMessage = exceptionMessage.substring(exceptionMessage.indexOf("CONSTRAINT `") + 12);
			exceptionMessage = exceptionMessage.substring(0, exceptionMessage.indexOf("`"));
		} else {
			exceptionMessage = null;
		}

		return exceptionMessage;
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	ResponseEntity<?> methodArgumentNotValidException(final MethodArgumentNotValidException e,
			final HttpServletRequest req) {

		final BindingResult bindingResult = e.getBindingResult();
		final List<FieldError> fieldErrors = bindingResult.getFieldErrors();
		final List<String> listValidationError = processFieldErrors(fieldErrors);

		final Map<String, Object> result = new LinkedHashMap<>();
		result.put("errorCode", "GEN-1");
		final String msg = env.getProperty("error.msg.GEN-1");
		result.put("errorMessage", msg != null ? msg : "Parâmetros inválidos");
		result.put("errorList", listValidationError);

		result.put("remoteAddress", getRemoteAddress(req));
		result.put("path", req.getRequestURI());
		result.put("forward", req.getAttribute(RequestDispatcher.FORWARD_REQUEST_URI));
		result.put("textoSuporte", env.getProperty("textoSuporteGenerico"));

		logger.warn(result.toString());
		return ResponseEntity.badRequest().body(result);

	}

	private List<String> processFieldErrors(final List<FieldError> fieldErrors) {
		final List<String> listValidationError = new ArrayList<>();
		for (final FieldError fieldError : fieldErrors) {
			String fieldKey = "field." + fieldError.getObjectName() + "." + fieldError.getField();
			fieldKey = fieldKey.replaceAll("(\\[.\\])", "");
			String fieldName = env.getProperty(fieldKey);
			if (fieldName == null) {
				fieldName = fieldError.getField();
			}

			String errorMessage = env.getProperty("error.constraint." + fieldError.getCode());
			if (fieldError.getCode().equals("Size")) {
				errorMessage = MessageFormat.format(errorMessage, fieldName, fieldError.getArguments()[2],
						fieldError.getArguments()[1]);
			} else if (fieldError.getCode().equals("Pattern")) {
				errorMessage = MessageFormat.format(errorMessage, fieldName, fieldError.getDefaultMessage());
			} else {
				errorMessage = MessageFormat.format(errorMessage, fieldName);
			}

			listValidationError.add(errorMessage);
		}
		return listValidationError;
	}

	/**
	 * Retorna o remote address verificando se existe o header X-Forwarder-For que é
	 * retornado quando o serviço está atrás de um proxy.
	 *
	 * @param request
	 * @return
	 */
	private String getRemoteAddress(final HttpServletRequest request) {

		String remoteAddress = request.getRemoteAddr();
		final String xForwardedFor = request.getHeader("X-Forwarded-For");
		if (xForwardedFor != null) {
			remoteAddress = xForwardedFor + ", " + remoteAddress;
		}
		return remoteAddress;
	}
}
