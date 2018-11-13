package br.com.joelamalio.calendar.domain;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import br.com.joelamalio.calendar.domain.validator.annotation.ValidPeriodo2Datas;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "periodo")
@Data
@EqualsAndHashCode(of = { "id" })
@ValidPeriodo2Datas
public class Periodo implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "periodo_seq", sequenceName = "periodo_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "periodo_seq")
	private Long id;

	@NotNull(message = "O campo Data Inicial é obrigatório")
	private LocalDate dataInicial;
	
	private LocalDate dataFinal;

	@NotBlank(message = "O campo Descrição é obrigatório")
	private String descricao;

	private Boolean status = Boolean.TRUE;
	
	public boolean isNovo() {
		return id == null;
	}

}
