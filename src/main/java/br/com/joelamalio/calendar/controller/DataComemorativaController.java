package br.com.joelamalio.calendar.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/data")
public class DataController {

	@GetMapping("/nova")
	public String nova() {
		return "data/manter";
	}
	
}
