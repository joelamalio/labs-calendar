package br.com.joelamalio.calendar.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.joelamalio.calendar.domain.Periodo;
import br.com.joelamalio.calendar.repository.helper.periodo.PeriodoRepositoryQueries;

@Repository
public interface PeriodoRepository extends JpaRepository<Periodo, Long>, PeriodoRepositoryQueries {

}
