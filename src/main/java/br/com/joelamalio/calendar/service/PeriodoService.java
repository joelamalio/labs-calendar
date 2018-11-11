package br.com.joelamalio.calendar.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.joelamalio.calendar.domain.Periodo;
import br.com.joelamalio.calendar.exception.RegistroDuplicadoException;
import br.com.joelamalio.calendar.repository.PeriodoRepository;
import br.com.joelamalio.calendar.repository.filter.PeriodoFilter;

@Service
public class PeriodoService {

	@Autowired
	private PeriodoRepository periodoRepository;

	@Transactional
	public Periodo salvar(Periodo periodo) {
		Optional<Periodo> optional = periodoRepository.validarDuplicidade(periodo);
		if (optional.isPresent()) {
			throw new RegistroDuplicadoException("O período já foi cadastrado anteriormente");
		}

		return periodoRepository.saveAndFlush(periodo);
	}

	@Transactional(propagation = Propagation.SUPPORTS)
	public List<Periodo> filtrar(PeriodoFilter filter) {
		return periodoRepository.filtrar(filter);
	}

}
