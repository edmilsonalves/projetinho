package br.com.sisteminha.controller;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

public class BasicMBean implements Serializable {

	private static final long serialVersionUID = -1044104803384321305L;

	public static boolean isPostBack() {
		return FacesContext.getCurrentInstance().isPostback();
	}

	public static boolean isNotPostBack() {
		return !FacesContext.getCurrentInstance().isPostback();
	}

	public static void addErrorMessage(String message) {
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_ERROR, message, message));

		FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
	}

	public static void addInfoMessage(String message) {
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, message, message));

		FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
	}

	protected FacesContext getFacesContext() {
		return FacesContext.getCurrentInstance();
	}

	protected ExternalContext getExternalContext() {
		return getFacesContext().getExternalContext();
	}

	protected Map<String, Object> getSessionMap() {
		return getExternalContext().getSessionMap();
	}

	@SuppressWarnings("unchecked")
	public <T> T getFlashAttribute(final String key) {
		return (T) getExternalContext().getFlash().get(key);
	}

	public void putFlashAttribute(final String key, final Object value) {
		getExternalContext().getFlash().put(key, value);

	}

	public String formatDateTime(final Date date) {
		final SimpleDateFormat SIMPLEDATETIMEFORMAT = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

		if (date != null) {
			return SIMPLEDATETIMEFORMAT.format(date);
		}

		return null;
	}

	@SuppressWarnings("unchecked")
	public <T> T getSessionAttribute(final String key) {
		return (T) getSessionMap().get(key);
	}

	public void putSessionAttribute(final String key, final Object value) {
		this.getSessionMap().put(key, value);

	}

}
