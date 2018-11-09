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

import br.com.joelamalio.calendar.exception.RegistroDuplicadoException;
import br.com.joelamalio.calendar.model.DataComemorativa;
import br.com.joelamalio.calendar.service.DataComemorativaService;

@Controller
@RequestMapping("/datas")
public class DataComemorativaController {
	
	@Autowired
	private DataComemorativaService dataComemorativaService;

	@GetMapping("/nova")
	public ModelAndView nova(DataComemorativa dataComemorativa) {
		ModelAndView mv = new ModelAndView("data/manter");
		return mv;
	}
	
	@PostMapping("/nova")
	public ModelAndView salvar(@Valid DataComemorativa dataComemorativa, BindingResult result, RedirectAttributes attributes) {
		if (result.hasErrors()) {			
			return nova(dataComemorativa);
		}
		
		try {
			dataComemorativaService.salvar(dataComemorativa);
			attributes.addFlashAttribute("mensagem", "A Data foi salva com sucesso!");
		} catch(RegistroDuplicadoException e) {
			result.rejectValue("data", e.getMessage(), e.getMessage());
			return nova(dataComemorativa);
		}
		
		return new ModelAndView("redirect:/datas/nova");
	}
	
}
