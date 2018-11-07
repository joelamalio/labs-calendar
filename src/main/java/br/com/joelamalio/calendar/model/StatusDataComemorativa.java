package br.com.joelamalio.calendar.model;

public enum StatusDataComemorativa {

	ATIVO("Ativao"), 
	INATIVO("Inativo"); 

	private String descricao;

	private StatusDataComemorativa(String descricao) {
		this.descricao = descricao;
	}
	
	public String getDescricao() {
		return descricao;
	}

}
