package br.com.sisteminha.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import org.apache.log4j.Logger;

public class StringUtils {

	private static final Logger logger = Logger.getLogger(StringUtils.class);

	public static String formatDate(final String data) {
		try {
			final DateFormat dtIn = new SimpleDateFormat("yyyyMMdd");
			final DateFormat dtOut = new SimpleDateFormat("dd/MM/yyyy");
			return dtOut.format(dtIn.parse(data));
		} catch (final Exception e) {
			logger.warn("Data em formato inválido");
		}
		return data;
	}

	public static String formatCompetencia(final String competencia) {
		try {
			final DateFormat dtIn = new SimpleDateFormat("yyyyMM");
			final DateFormat dtOut = new SimpleDateFormat("MM/yyyy");
			return dtOut.format(dtIn.parse(competencia));
		} catch (final Exception e) {
			logger.warn("Data em formato inválido {}");
		}
		return competencia;

	}
}
