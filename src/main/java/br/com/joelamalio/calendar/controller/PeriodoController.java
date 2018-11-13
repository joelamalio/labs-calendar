package br.com.joelamalio.calendar.controller;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.joelamalio.calendar.controller.page.PageWrapper;
import br.com.joelamalio.calendar.domain.Periodo;
import br.com.joelamalio.calendar.exception.RegistroDuplicadoException;
import br.com.joelamalio.calendar.repository.PeriodoRepository;
import br.com.joelamalio.calendar.repository.filter.PeriodoFilter;
import br.com.joelamalio.calendar.service.PeriodoService;

@Controller
@RequestMapping("/periodo")
public class PeriodoController {
	
	@Autowired
	private PeriodoService periodoService;

	@Autowired
	private PeriodoRepository periodoRepository;

	@GetMapping("/novo")
	public ModelAndView novo(Periodo periodo) {
		ModelAndView mv = new ModelAndView("periodo/manter-periodo");
		return mv;
	}
	
	@PostMapping(value = { "/novo", "{\\d+}" })
	public ModelAndView salvar(@Valid Periodo periodo, BindingResult result, RedirectAttributes attributes) {
		if (result.hasErrors()) {			
			return novo(periodo);
		}
		
		boolean isNovo = periodo.isNovo();
		
		try {
			periodoService.salvar(periodo);
		} catch(RegistroDuplicadoException e) {
			result.rejectValue("dataInicial", e.getMessage(), e.getMessage());
			return novo(periodo);
		}
		
		String mensagem;
		if (isNovo) {
			mensagem = "O registro Período foi salvo.";
			attributes.addFlashAttribute("mensagem", mensagem);
			return new ModelAndView("redirect:/periodo/novo");
		}

		mensagem = String.format("O registro Período '%d' foi alterado.", periodo.getId());
		return novo(periodo).addObject("mensagem", mensagem);
	}
	
	@GetMapping
	public ModelAndView pesquisar(PeriodoFilter periodoFilter, @PageableDefault(size = 2) Pageable pageable, HttpServletRequest httpServletRequest) {
		ModelAndView mv = new ModelAndView("periodo/listar-periodo");
		PageWrapper<Periodo> paginaWrapper = new PageWrapper<Periodo>(periodoRepository.filtrar(periodoFilter, pageable), httpServletRequest);
		mv.addObject("pagina", paginaWrapper);
		
		return mv;
	}
	
	@GetMapping("/{id}")
	public ModelAndView editar(@PathVariable("id") Long id) {
		Optional<Periodo> optional = periodoRepository.findById(id);
		
		if (optional.isPresent()) {
			Periodo periodo = optional.get();
			ModelAndView mv = novo(periodo);
			mv.addObject(periodo);
			return mv;
		}
		
		return null;
	}
	
	@GetMapping("/teste-excluir/{id}")
	public ModelAndView excluir(@PathVariable("id") Long id, RedirectAttributes attributes) {
		Optional<Periodo> optional = periodoRepository.findById(id);
		
		if (optional.isPresent()) {
			periodoRepository.deleteById(id);
			attributes.addFlashAttribute("mensagem", String.format("O Período registro '%d' foi excluído.", id));
			return new ModelAndView("redirect:/periodo");
		}
		
		return null;
	}
	
}
