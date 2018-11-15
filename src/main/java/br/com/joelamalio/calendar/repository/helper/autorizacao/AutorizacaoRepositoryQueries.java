package br.com.joelamalio.calendar.repository.helper.autorizacao;

import java.util.List;

import br.com.joelamalio.calendar.domain.Usuario;

public interface AutorizacaoRepositoryQueries {

	public List<String> obter(Usuario usuario);
	
}
