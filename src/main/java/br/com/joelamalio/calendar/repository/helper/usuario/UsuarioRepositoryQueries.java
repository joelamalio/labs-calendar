package br.com.joelamalio.calendar.repository.helper.usuario;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.joelamalio.calendar.domain.Usuario;
import br.com.joelamalio.calendar.repository.filter.UsuarioFilter;

public interface UsuarioRepositoryQueries {

	public Optional<Usuario> logar(String login);
	
	public Optional<Usuario> validarDuplicidade(Usuario usuario);
	
	public Page<Usuario> filtrar(UsuarioFilter filter, Pageable pageable);
	
}
