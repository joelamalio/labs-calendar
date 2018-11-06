package br.com.joelamalio.calendar.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/status")
public class StatusUsuarioController {

	@GetMapping
	public String listar() {
		return "status-usuario/listar";
	}
	
}
