package br.com.joelamalio.calendar.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.joelamalio.calendar.domain.Periodo;
import br.com.joelamalio.calendar.exception.RegistroDuplicadoException;
import br.com.joelamalio.calendar.repository.PeriodoRepository;

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

}
