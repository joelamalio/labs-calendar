package br.com.joelamalio.calendar.repository.filter;

import java.io.Serializable;
import java.time.LocalDate;

import lombok.Data;

@Data
public class PeriodoFilter implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private LocalDate dataInicial;
	
	private LocalDate dataFinal;
	
}
