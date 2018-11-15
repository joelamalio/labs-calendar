package br.com.joelamalio.calendar.domain;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.PostLoad;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.util.StringUtils;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "usuario")
@Data
@EqualsAndHashCode(of = { "id" })
public class Usuario implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@SequenceGenerator(name = "usuario_seq", sequenceName = "usuario_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "usuario_seq")
	private Long id;

	@Size(max = 50, message = "O tamanho máximo permitido do campo Nome é {max}")
	@NotBlank(message = "O campo Nome é obrigatório")
	private String nome;
	
	@Size(max = 100, message = "O tamanho máximo permitido do campo Email é {max}")
	@Email(message = "Email inválido")
	@NotBlank(message = "Email é obrigatório")
	private String email;
	
	private String senha;
	
	@Transient
	private String confirmacaoSenha;
	
	@Size(max = 20, message = "O tamanho máximo permitido do campo Login é {max}")
	@NotBlank(message = "O campo Login é obrigatório")
	private String login;
	
	private Boolean status;
	
	@Size(min = 0, message = "Selecione pelo menos um perfil")
	@ManyToMany
	@JoinTable(name = "usuario_perfil", joinColumns = @JoinColumn(name = "id_usuario"), inverseJoinColumns = @JoinColumn(name = "id_perfil"))
	private List<Perfil> perfis ;
	
	@Transient
	private String nomeApresentacao;

	@PrePersist
	private void prePersistUpdate() {
		login = login.toLowerCase();
		email = email.toLowerCase();
	}
	
	@PreUpdate
	private void preUpdate() {
		this.confirmacaoSenha = this.senha;
		login = login.toLowerCase();
		email = email.toLowerCase();
	}
	
	@PostLoad
	private void PostLoad() {
		if (!StringUtils.isEmpty(this.nome)) {
			this.nomeApresentacao = this.nome.split(" ")[0];
			if (this.nomeApresentacao.length() > 14) {
				this.nomeApresentacao = this.nomeApresentacao.substring(0, 14);
			}
		}
	}
	
	public boolean isNovo() {
		return id == null;
	}
	
}
