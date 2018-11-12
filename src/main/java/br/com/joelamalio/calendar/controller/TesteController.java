package br.com.joelamalio.calendar.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TesteController {

	@GetMapping("/teste")
	public String teste() {
		return "teste";
	}
	
	@GetMapping("/teste2")
	public String teste2() {
		return "teste2";
	}
	
}
