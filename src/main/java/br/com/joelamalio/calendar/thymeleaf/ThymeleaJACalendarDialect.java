package br.com.joelamalio.calendar.thymeleaf;

import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.joelamalio.calendar.thymeleaf.dialect.JACalendarDialect;

@Configuration
@ConditionalOnClass(JACalendarDialect.class)
public class ThymeleaJACalendarDialect {
	
	@Bean
	@ConditionalOnMissingBean
	public JACalendarDialect jaCalendarDialect() {
		return new JACalendarDialect();
	}

}
