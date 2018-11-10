package br.com.joelamalio.calendar.repository.helper.periodo;

import java.time.LocalDate;
import java.util.Optional;

import br.com.joelamalio.calendar.domain.Periodo;

public interface PeriodoRepositoryQueries {

	public Optional<Periodo> obterPor(LocalDate dataInicial);
	
}
