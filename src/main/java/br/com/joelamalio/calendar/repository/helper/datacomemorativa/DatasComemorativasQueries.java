package br.com.joelamalio.calendar.repository.helper.datacomemorativa;

import java.time.LocalDate;
import java.util.Optional;

import br.com.joelamalio.calendar.model.DataComemorativa;

public interface DatasComemorativasQueries {

	public Optional<DataComemorativa> obterPor(LocalDate data);
	
}
