package br.com.joelamalio.calendar.security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import br.com.joelamalio.calendar.domain.Usuario;

public class UsuarioSecurity extends User {

	private static final long serialVersionUID = 1L;

	private Usuario usuario;

	public UsuarioSecurity(Usuario usuario, Collection<? extends GrantedAuthority> authorities) {
		super(usuario.getLogin(), usuario.getSenha(), authorities);
		this.usuario = usuario;
	}

	public Usuario getUsuario() {
		return usuario;
	}

}
