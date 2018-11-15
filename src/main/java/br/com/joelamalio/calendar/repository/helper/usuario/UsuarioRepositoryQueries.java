package br.com.joelamalio.calendar.repository.helper.usuario;

import java.util.Optional;

import br.com.joelamalio.calendar.domain.Usuario;

public interface UsuarioRepositoryQueries {

	public Optional<Usuario> logar(String login);
	
}
