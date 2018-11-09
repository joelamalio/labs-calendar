package br.com.joelamalio.calendar.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.joelamalio.calendar.model.DataComemorativa;
import br.com.joelamalio.calendar.repository.helper.datacomemorativa.DatasComemorativasQueries;

@Repository
public interface DatasComemorativas extends JpaRepository<DataComemorativa, Long>, DatasComemorativasQueries {

}
