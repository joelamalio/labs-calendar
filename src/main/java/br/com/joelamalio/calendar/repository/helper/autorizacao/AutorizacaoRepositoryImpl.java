package br.com.joelamalio.calendar.repository.helper.autorizacao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.joelamalio.calendar.domain.Usuario;

public class AutorizacaoRepositoryImpl implements AutorizacaoRepositoryQueries {

	@PersistenceContext
	private EntityManager em;

	@Override
	public List<String> obter(Usuario usuario) {
		StringBuilder query = new StringBuilder();
		query.append(" SELECT DISTINCT a.nome ");
		query.append(" FROM Usuario u ");
		query.append(" INNER JOIN u.perfis p ");
		query.append(" INNER JOIN p.autorizacoes a ");
		query.append(" WHERE u = :usuario ");
		
		return em.createQuery(query.toString(), String.class).setParameter("usuario", usuario).getResultList();
	}

}
