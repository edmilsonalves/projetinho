package br.com.sisteminha.util.jsf;

import java.util.Arrays;
import java.util.List;

public class BusinessException
		extends RuntimeException
{

	private final QuerErrorCode errorCode;
	private Object[] parameters;

	private static final long serialVersionUID = 1L;

	public BusinessException (final QuerErrorCode errorCode)
	{
		super(errorCode.getMensagem());
		this.errorCode = errorCode;
	}

	public BusinessException (final QuerErrorCode errorCode, final Object... parameters)
	{
		super(errorCode.getMensagem());
		this.errorCode = errorCode;
		this.parameters = parameters;
	}

	public BusinessException (final QuerErrorCode errorCode, final Throwable cause, final Object... parameters)
	{
		super(errorCode.getMensagem(), cause);
		this.errorCode = errorCode;
		this.parameters = parameters;
	}

	public final QuerErrorCode getErrorCode ()
	{
		return errorCode;
	}

	public List<Object> getParameters ()
	{

		if (parameters != null)
		{
			return Arrays.asList(parameters);
		}

		return null;
	}

	public void setParameters (Object[] parameters)
	{
		this.parameters = parameters;
	}

}
