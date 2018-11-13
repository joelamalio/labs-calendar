package br.com.joelamalio.calendar.repository.helper.periodo;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.transaction.annotation.Transactional;

import br.com.joelamalio.calendar.domain.Periodo;
import br.com.joelamalio.calendar.repository.filter.PeriodoFilter;

public class PeriodoRepositoryImpl implements PeriodoRepositoryQueries {

	@PersistenceContext
	private EntityManager em;

	@Override
	public Optional<Periodo> validarDuplicidade(Periodo periodo) {
		Criteria criteria = em.unwrap(Session.class).createCriteria(Periodo.class);
		adicionarFiltro(periodo, criteria);

		return Optional.ofNullable((Periodo) criteria.uniqueResult());
	}
	
	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly = true)
	public List<Periodo> filtrar(PeriodoFilter filter) {
		Criteria criteria = em.unwrap(Session.class).createCriteria(Periodo.class);
		
		adicionarFiltro(filter, criteria);
		
		return criteria.list();
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
