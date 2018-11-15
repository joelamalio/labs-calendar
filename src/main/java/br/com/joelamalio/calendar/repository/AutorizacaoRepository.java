package br.com.joelamalio.calendar.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.joelamalio.calendar.domain.Autorizacao;
import br.com.joelamalio.calendar.repository.helper.autorizacao.AutorizacaoRepositoryQueries;

@Repository
public interface AutorizacaoRepository extends JpaRepository<Autorizacao, Long>, AutorizacaoRepositoryQueries {

}