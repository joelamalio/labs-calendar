package br.com.joelamalio.calendar.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.joelamalio.calendar.exception.RegistroDuplicadoException;
import br.com.joelamalio.calendar.model.DataComemorativa;
import br.com.joelamalio.calendar.repository.DatasComemorativas;

@Service
public class DataComemorativaService {

	@Autowired
	private DatasComemorativas datasComemorativas;

	@Transactional
	public DataComemorativa salvar(DataComemorativa dataComemorativa) {
		Optional<DataComemorativa> optional = datasComemorativas.obterPor(dataComemorativa.getData());
		if (optional.isPresent()) {
			throw new RegistroDuplicadoException("A Data já foi cadastrada");
		}

		return datasComemorativas.saveAndFlush(dataComemorativa);
	}

	@Transactional(propagation = Propagation.SUPPORTS)
	public List<DataComemorativa> filtrar() {
		return datasComemorativas.findAll();
	}

}
