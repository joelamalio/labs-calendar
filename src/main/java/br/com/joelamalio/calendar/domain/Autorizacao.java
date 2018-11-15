package br.com.joelamalio.calendar.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "autorizacao")
@Data
@EqualsAndHashCode(of = { "id" })
public class Autorizacao implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	private Long id;
	
	private String nome;
	
}
