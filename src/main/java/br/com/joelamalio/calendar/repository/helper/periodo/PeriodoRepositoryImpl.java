package br.com.joelamalio.calendar.repository.helper.periodo;

import java.time.LocalDate;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import br.com.joelamalio.calendar.domain.Periodo;

public class PeriodoRepositoryImpl implements PeriodoRepositoryQueries {

	@PersistenceContext
	private EntityManager em;

	@Override
	public Optional<Periodo> obterPor(LocalDate dataInicial) {
		Criteria criteria = em.unwrap(Session.class).createCriteria(Periodo.class);
		adicionarFiltro(dataInicial, criteria);

		return Optional.ofNullable((Periodo) criteria.uniqueResult());
	}

	private void adicionarFiltro(LocalDate dataInicial, Criteria criteria) {
		if (dataInicial != null) {
			criteria.add(Restrictions.eq("dataInicial", dataInicial));
		}
	}

}
