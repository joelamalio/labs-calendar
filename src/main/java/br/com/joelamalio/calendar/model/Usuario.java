package br.com.joelamalio.calendar.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "usuario")
@Data
@EqualsAndHashCode(of = { "id" })
public class Usuario implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank(message = "O campo Nome é obrigatório")
	private String nome;
	
	@Email(message = "E-mail inválido")
	@NotBlank(message = "E-mail é obrigatório")
	private String email;
	
	@NotBlank(message = "O campo Senha é obrigatório")
	private String senha;
	
	@NotBlank(message = "O campo Login é obrigatório")
	private String login;
	
	@Enumerated(EnumType.STRING)
	private StatusUsuario status = StatusUsuario.PENDENTE_ATIVACAO;
	
}
