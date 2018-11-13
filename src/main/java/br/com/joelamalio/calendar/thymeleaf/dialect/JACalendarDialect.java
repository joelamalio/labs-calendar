package br.com.joelamalio.calendar.thymeleaf.dialect;

import java.util.HashSet;
import java.util.Set;

import org.thymeleaf.dialect.AbstractProcessorDialect;
import org.thymeleaf.processor.IProcessor;
import org.thymeleaf.standard.StandardDialect;

import br.com.joelamalio.calendar.thymeleaf.processor.PaginationElementTagProcessor;

public class JACalendarDialect extends AbstractProcessorDialect {

	public JACalendarDialect() {
		super("JA Calendar", "jacalendar", StandardDialect.PROCESSOR_PRECEDENCE);
	}
	
	@Override
	public Set<IProcessor> getProcessors(String dialectPrefix) {
		final Set<IProcessor> processadores = new HashSet<IProcessor>();
		processadores.add(new PaginationElementTagProcessor(dialectPrefix));
		return processadores;
	}

}