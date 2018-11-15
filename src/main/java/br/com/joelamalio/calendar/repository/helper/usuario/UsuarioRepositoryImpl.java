package br.com.joelamalio.calendar.repository.helper.usuario;

import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.joelamalio.calendar.domain.Usuario;

public class UsuarioRepositoryImpl implements UsuarioRepositoryQueries {

	@PersistenceContext
	private EntityManager em;
	
	@Override
	public Optional<Usuario> logar(String login) {
		return em.createQuery(" FROM Usuario WHERE login = :login AND status = true ", Usuario.class)
				  .setParameter("login", login).getResultList().stream().findFirst();
	}

}
