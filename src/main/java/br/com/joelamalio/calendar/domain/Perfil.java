package br.com.joelamalio.calendar.domain;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "perfil")
@Data
@EqualsAndHashCode(of = { "id" })
public class Perfil implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	private Long id;

	private String nome;

	@ManyToMany
	@JoinTable(name = "perfil_autorizacao", joinColumns = @JoinColumn(name = "id_perfil"), inverseJoinColumns = @JoinColumn(name = "id_autorizacao"))
	private List<Autorizacao> autorizacoes;

}
