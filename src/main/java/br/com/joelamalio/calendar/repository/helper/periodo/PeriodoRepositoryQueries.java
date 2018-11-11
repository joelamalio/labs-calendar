package br.com.joelamalio.calendar.repository.helper.periodo;

import java.util.List;
import java.util.Optional;

import br.com.joelamalio.calendar.domain.Periodo;
import br.com.joelamalio.calendar.repository.filter.PeriodoFilter;

public interface PeriodoRepositoryQueries {

	public Optional<Periodo> validarDuplicidade(Periodo periodo);

	public List<Periodo> filtrar(PeriodoFilter filter);
	
}
