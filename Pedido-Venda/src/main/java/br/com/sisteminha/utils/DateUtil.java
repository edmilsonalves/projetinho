package br.com.sisteminha.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {
	public static final String FMT_DD_MM_YYYY_HH_MM = "dd/MM/yyyy HH:mm";
	public static final String FMT_DD_MM_YYYY_HH_MM_SEM_FORMATACAO = "ddMMyyyyHHmm";
	public static final String FMT_DD_MM_YYYY = "dd/MM/yyyy";
	public static final String FMT_HH_MM_SS = "HH:mm:ss";

	public static final String FMT_ISO_DATE_TIME = "yyyy-MM-dd'T'HH:mm:ssZ";
	public static final String FMT_ISO_DATE = "yyyy-MM-";

	private DateUtil() {
	}

	public static String getDateTimeToString(Date data) {
		DateFormat dateFormat = new SimpleDateFormat(FMT_DD_MM_YYYY_HH_MM);
		return dateFormat.format(data);
	}

	public static String getDateTimeToStringSemFormatacao(Date data) {
		DateFormat dateFormat = new SimpleDateFormat(FMT_DD_MM_YYYY_HH_MM_SEM_FORMATACAO);
		return dateFormat.format(data);
	}

	public static String getDateToString(Date data, String mask) {
		DateFormat dateFormat = new SimpleDateFormat(mask);
		return dateFormat.format(data);
	}

	public static String getDateToString(Date data) {
		DateFormat dateFormat = new SimpleDateFormat(FMT_DD_MM_YYYY);
		return dateFormat.format(data);
	}

	public static String getTimeToString(Date data) {
		DateFormat dateFormat = new SimpleDateFormat(FMT_HH_MM_SS);
		return dateFormat.format(data);
	}

	public static Date subtrairDias(Date data, Integer numeroDias) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(data);
		calendar.set(Calendar.DATE, calendar.get(Calendar.DATE) - numeroDias);
		return calendar.getTime();
	}

	public static Date subtrairHoras(Date data, Integer numeroHora) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(data);
		calendar.set(Calendar.HOUR_OF_DAY, calendar.get(Calendar.HOUR_OF_DAY) - numeroHora);
		return calendar.getTime();
	}

	public static Date adicionarHoras(Date data, Integer numeroHora) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(data);
		calendar.set(Calendar.HOUR_OF_DAY, calendar.get(Calendar.HOUR_OF_DAY) + numeroHora);
		return calendar.getTime();
	}

	public static Date adicionarDias(Date data, Integer numeroDias) {
		if (numeroDias != null) {
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(data);
			calendar.set(Calendar.DATE, calendar.get(Calendar.DATE) + numeroDias);
			return calendar.getTime();
		}
		return null;
	}

	public static Date zerarHora(Date data) {
		if (data == null) {
			return null;
		}
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(data);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		return calendar.getTime();
	}

	public static Date setarHora(Date data, int hora, int minuto, int segundo) {
		if (data == null) {
			return null;
		}
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(data);
		calendar.set(Calendar.HOUR_OF_DAY, hora);
		calendar.set(Calendar.MINUTE, minuto);
		calendar.set(Calendar.SECOND, segundo);
		calendar.set(Calendar.MILLISECOND, 0);
		return calendar.getTime();
	}

	public static Date getFormattedDate(final Date date, final String format) {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		try {
			return sdf.parse(sdf.format(date));
		} catch (ParseException e) {
			return date;
		} catch (Exception e) {
			return null;
		}
	}

	public static boolean compareDates(final Date date1, final Date date2) {
		SimpleDateFormat sdf = new SimpleDateFormat(FMT_DD_MM_YYYY);
		return sdf.format(date1).equals(sdf.format(date2));
	}

	public static String toDateTimeIsoString(final Date date) {
		if (date == null)
			return null;

		SimpleDateFormat sdf = new SimpleDateFormat(FMT_ISO_DATE_TIME);
		return sdf.format(date);
	}

	public static String toDateIsoString(final Date date) {
		if (date == null)
			return null;

		SimpleDateFormat sdf = new SimpleDateFormat(FMT_ISO_DATE);
		return sdf.format(date);
	}

}
