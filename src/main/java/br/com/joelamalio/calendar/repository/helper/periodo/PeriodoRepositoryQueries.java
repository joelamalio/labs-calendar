package br.com.joelamalio.calendar.repository.helper.periodo;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.joelamalio.calendar.domain.Periodo;
import br.com.joelamalio.calendar.repository.filter.PeriodoFilter;

public interface PeriodoRepositoryQueries {

	public Optional<Periodo> validarDuplicidade(Periodo periodo);

	public Page<Periodo> filtrar(PeriodoFilter filter, Pageable pageable);
	
}
