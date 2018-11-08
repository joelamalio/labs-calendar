package br.com.joelamalio.calendar.config.format;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Locale;

import org.springframework.format.Formatter;

public class LocalDateFormatter implements Formatter<LocalDate> {

	@Override
	public LocalDate parse(String text, Locale locale) throws ParseException {
		return LocalDate.parse(text, DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM));
	}

	@Override
	public String print(LocalDate object, Locale locale) {
		return DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM).format(object);
	}
	
}
