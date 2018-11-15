package br.com.joelamalio.calendar.repository.helper.usuario;

import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import br.com.joelamalio.calendar.domain.Usuario;
import br.com.joelamalio.calendar.repository.filter.UsuarioFilter;
import br.com.joelamalio.calendar.repository.pagination.PaginacaoUtil;

public class UsuarioRepositoryImpl implements UsuarioRepositoryQueries {

	@PersistenceContext
	private EntityManager em;
	
	@Autowired
	private PaginacaoUtil paginacaoUtil; 
	
	@Override
	public Optional<Usuario> logar(String login) {
		return em.createQuery(" FROM Usuario WHERE login = :login AND status = true ", Usuario.class)
				  .setParameter("login", login).getResultList().stream().findFirst();
	}
	
	@Override
	public Optional<Usuario> validarDuplicidade(Usuario usuario) {
		Criteria criteria = em.unwrap(Session.class).createCriteria(Usuario.class);

		if (!StringUtils.isEmpty(usuario.getLogin())) {
			criteria.add(Restrictions.eq("login", usuario.getLogin()));
		}

		return Optional.ofNullable((Usuario) criteria.uniqueResult());
	}
	
	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly = true)
	public Page<Usuario> filtrar(UsuarioFilter filter, Pageable pageable) {
		Criteria criteria = em.unwrap(Session.class).createCriteria(Usuario.class);
		
		paginacaoUtil.adicionar(criteria, pageable);
		
		adicionarFiltro(filter, criteria);
		
		return new PageImpl<Usuario>(criteria.list(), pageable, total(filter));
	}
	
	private Long total(UsuarioFilter filter) {
		Criteria criteria = em.unwrap(Session.class).createCriteria(Usuario.class);
		adicionarFiltro(filter, criteria);
		criteria.setProjection(Projections.rowCount());
		return (Long) criteria.uniqueResult();
	}

	private void adicionarFiltro(UsuarioFilter filter, Criteria criteria) {
		if (!StringUtils.isEmpty(filter.getNome())) {
			criteria.add(Restrictions.ilike("nome", filter.getNome(), MatchMode.ANYWHERE));
		}
	}

}
