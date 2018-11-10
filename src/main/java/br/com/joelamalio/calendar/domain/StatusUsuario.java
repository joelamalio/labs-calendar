package br.com.joelamalio.calendar.domain;

public enum StatusUsuario {

	ATIVO("Ativo"), 
	INATIVO("Inativo"), 
	PENDENTE_ATIVACAO("Pendente Ativação");

	private String descricao;

	private StatusUsuario(String descricao) {
		this.descricao = descricao;
	}
	
	public String getDescricao() {
		return descricao;
	}

}
