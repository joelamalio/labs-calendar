package br.com.joelamalio.calendar.repository.filter;

import java.io.Serializable;

import lombok.Data;

@Data
public class UsuarioFilter implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String nome;
	
}
