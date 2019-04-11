package br.com.sisteminha.util.jsf;

import java.io.IOException;
import java.util.Iterator;
import javax.faces.FacesException;
import javax.faces.application.ViewExpiredException;
import javax.faces.context.ExceptionHandler;
import javax.faces.context.ExceptionHandlerWrapper;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ExceptionQueuedEvent;
import javax.faces.event.ExceptionQueuedEventContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import br.com.sisteminha.service.BusinessException;

/**
 *
 * @author Edmilson Reis
 */
public class JsfExceptionHandler extends ExceptionHandlerWrapper {

    private static Log log = LogFactory.getLog(JsfExceptionHandler.class);

    private ExceptionHandler wrapped;

    public JsfExceptionHandler(ExceptionHandler wrapped) {
        this.wrapped = wrapped;
    }

    @Override
    public ExceptionHandler getWrapped() {
        return this.wrapped;
    }

    @Override
    public void handle() throws FacesException {
        Iterator<ExceptionQueuedEvent> events = getUnhandledExceptionQueuedEvents().iterator();
        while (events.hasNext()) {
            ExceptionQueuedEvent event = events.next();
            ExceptionQueuedEventContext context = (ExceptionQueuedEventContext) event.getSource();

            //Recupera a exception
            Throwable exception = context.getException();

            BusinessException negocioException = getNegocioException(exception);

            boolean handled = false; //tratado

            /* Tratamento da Exception */
            try {
                if (exception instanceof ViewExpiredException) {
                    handled = true;
                    redirect("/"); //Redireciona para a Tela Inicial

                } else if (negocioException != null) { //Se for negócio exception
                    handled = true;
                    FacesUtil.addErrorMessage(negocioException.getMessage());
                } else {
                    handled = true;
                    log.error("Erro de sistema: " + exception.getMessage(), exception); //Impl Apache commons logging

                    redirect("/erro.xhtml");
                }
            } finally {
                if (handled) {
                    events.remove();
                }
            }

            getWrapped().handle();
        }
    }

    //Desmonta as exceções para ver se á um NegocioException 
    private BusinessException getNegocioException(Throwable exception) {
        if (exception instanceof BusinessException) {
            return (BusinessException) exception;
        } else if (exception.getCause() != null) {
            return getNegocioException(exception.getCause());
        }

        return null;
    }

    private void redirect(String page) {
        try {
            FacesContext facesContext = FacesContext.getCurrentInstance();
            ExternalContext externalContext = facesContext.getExternalContext();
            String contextPath = externalContext.getRequestContextPath(); //Pega a Pasta do contexto

            externalContext.redirect(contextPath + page); //Redireciona para o caminho completo
            facesContext.responseComplete(); //Evita outro processamento

        } catch (IOException ex) {
            throw new FacesException(ex);
        }
    }
}
