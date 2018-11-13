package br.com.joelamalio.calendar.repository.helper.periodo;

import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import br.com.joelamalio.calendar.domain.Periodo;
import br.com.joelamalio.calendar.repository.filter.PeriodoFilter;
import br.com.joelamalio.calendar.repository.pagination.PaginacaoUtil;

public class PeriodoRepositoryImpl implements PeriodoRepositoryQueries {

	@PersistenceContext
	private EntityManager em;
	
	@Autowired
	private PaginacaoUtil paginacaoUtil; 

	@Override
	public Optional<Periodo> validarDuplicidade(Periodo periodo) {
		Criteria criteria = em.unwrap(Session.class).createCriteria(Periodo.class);
		adicionarFiltro(periodo, criteria);

		return Optional.ofNullable((Periodo) criteria.uniqueResult());
	}
	
	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly = true)
	public Page<Periodo> filtrar(PeriodoFilter filter, Pageable pageable) {
		Criteria criteria = em.unwrap(Session.class).createCriteria(Periodo.class);
		
		paginacaoUtil.adicionar(criteria, pageable);
		
		adicionarFiltro(filter, criteria);
		
		return new PageImpl<Periodo>(criteria.list(), pageable, total(filter));
	}
	
	private Long total(PeriodoFilter filter) {
		Criteria criteria = em.unwrap(Session.class).createCriteria(Periodo.class);
		adicionarFiltro(filter, criteria);
		criteria.setProjection(Projections.rowCount());
		return (Long) criteria.uniqueResult();
	}

	private void adicionarFiltro(Periodo periodo, Criteria criteria) {
		if (!periodo.isNovo()) {
			criteria.add(Restrictions.ne("id", periodo.getId()));
		}
		
		if (periodo.getDataInicial() != null && periodo.getDataFinal() != null) {
			criteria.add(Restrictions.eq("dataInicial", periodo.getDataInicial()));
			criteria.add(Restrictions.eq("dataFinal", periodo.getDataFinal()));
		} else {
			criteria.add(Restrictions.isNull("dataInicial"));
		}
	}
	
	private void adicionarFiltro(PeriodoFilter filter, Criteria criteria) {
		if (filter.getDataInicial() != null) {
			criteria.add(Restrictions.ge("dataInicial", filter.getDataInicial()));
		}
		
		if (filter.getDataFinal() != null) {
			criteria.add(Restrictions.le("dataFinal", filter.getDataFinal()));
		}
	}

}
