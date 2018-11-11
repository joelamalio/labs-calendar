package br.com.joelamalio.calendar.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.joelamalio.calendar.domain.Periodo;
import br.com.joelamalio.calendar.exception.RegistroDuplicadoException;
import br.com.joelamalio.calendar.repository.filter.PeriodoFilter;
import br.com.joelamalio.calendar.service.PeriodoService;

@Controller
@RequestMapping("/periodo")
public class PeriodoController {
	
	@Autowired
	private PeriodoService periodoService;

	@GetMapping("/novo")
	public ModelAndView nova(Periodo periodo) {
		ModelAndView mv = new ModelAndView("periodo/manter-periodo");
		return mv;
	}
	
	@PostMapping("/novo")
	public ModelAndView salvar(@Valid Periodo periodo, BindingResult result, RedirectAttributes attributes) {
		if (result.hasErrors()) {			
			return nova(periodo);
		}
		
		try {
			periodoService.salvar(periodo);
			attributes.addFlashAttribute("mensagem", "O Período foi salvo com sucesso!");
		} catch(RegistroDuplicadoException e) {
			result.rejectValue("dataInicial", e.getMessage(), e.getMessage());
			return nova(periodo);
		}
		
		return new ModelAndView("redirect:/periodo/novo");
	}
	
	@GetMapping
	public ModelAndView pesquisar(PeriodoFilter filter, BindingResult result) {
		ModelAndView mv = new ModelAndView("periodo/listar-periodo");
		mv.addObject("periodos", periodoService.filtrar(filter));
		return mv;
	}
	
}
