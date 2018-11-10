package br.com.joelamalio.calendar.repository.helper.periodo;

import java.util.Optional;

import br.com.joelamalio.calendar.domain.Periodo;

public interface PeriodoRepositoryQueries {

	public Optional<Periodo> validarDuplicidade(Periodo periodo);
	
}
