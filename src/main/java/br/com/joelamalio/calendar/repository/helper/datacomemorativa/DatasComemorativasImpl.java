package br.com.joelamalio.calendar.repository.helper.datacomemorativa;

import java.time.LocalDate;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import br.com.joelamalio.calendar.model.DataComemorativa;

public class DatasComemorativasImpl implements DatasComemorativasQueries {

	@PersistenceContext
	private EntityManager em;

	@Override
	public Optional<DataComemorativa> obterPor(LocalDate data) {
		Criteria criteria = em.unwrap(Session.class).createCriteria(DataComemorativa.class);
		adicionarFiltro(data, criteria);

		return Optional.ofNullable((DataComemorativa) criteria.uniqueResult());
	}

	private void adicionarFiltro(LocalDate data, Criteria criteria) {
		if (data != null) {
			criteria.add(Restrictions.eq("data", data));
		}
	}

}
