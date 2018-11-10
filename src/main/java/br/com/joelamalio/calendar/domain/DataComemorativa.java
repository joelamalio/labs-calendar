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

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "data_comemorativa")
@Data
@EqualsAndHashCode(of = { "id" })
public class DataComemorativa implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "data_comemorativa_seq", sequenceName = "data_comemorativa_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "data_comemorativa_seq")
	private Long id;

	@NotNull(message = "O campo Data é obrigatório")
	private LocalDate data;

	@NotBlank(message = "O campo Descrição é obrigatório")
	private String descricao;

	private Boolean status = Boolean.TRUE;

}
