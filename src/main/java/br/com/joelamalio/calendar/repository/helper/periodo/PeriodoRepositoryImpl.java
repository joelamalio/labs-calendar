package br.com.joelamalio.calendar.repository.helper.periodo;

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
	public Optional<Periodo> validarDuplicidade(Periodo periodo) {
		Criteria criteria = em.unwrap(Session.class).createCriteria(Periodo.class);
		adicionarFiltro(periodo, criteria);

		return Optional.ofNullable((Periodo) criteria.uniqueResult());
	}

	private void adicionarFiltro(Periodo periodo, Criteria criteria) {
		if (periodo.getDataInicial() != null && periodo.getDataFinal() != null) {
			criteria.add(Restrictions.eq("dataInicial", periodo.getDataInicial()));
			criteria.add(Restrictions.eq("dataFinal", periodo.getDataFinal()));
		} else {
			criteria.add(Restrictions.isNull("dataInicial"));
		}
	}

}
