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
import br.com.joelamalio.calendar.domain.Usuario;
import br.com.joelamalio.calendar.exception.RegistroDuplicadoException;
import br.com.joelamalio.calendar.repository.UsuarioRepository;
import br.com.joelamalio.calendar.repository.filter.UsuarioFilter;
import br.com.joelamalio.calendar.service.UsuarioService;

@Controller
@RequestMapping("/usuario")
public class UsuarioController {

	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@RequestMapping("/novo")
	public ModelAndView novo(Usuario usuario) {
		ModelAndView mv = new ModelAndView("usuario/manter-usuario");
		return mv;
	}
	
	@PostMapping(value = { "/novo", "{\\d+}" })
	public ModelAndView salvar(@Valid Usuario usuario, BindingResult result, RedirectAttributes attributes) {
		if (result.hasErrors()) {			
			return novo(usuario);
		}
		
		boolean isNovo = usuario.isNovo();
		
		try {
			usuarioService.salvar(usuario);
		} catch(RegistroDuplicadoException e) {
			result.rejectValue("dataInicial", e.getMessage(), e.getMessage());
			return novo(usuario);
		}
		
		String mensagem;
		if (isNovo) {
			mensagem = "O registro Usuário foi salvo.";
			attributes.addFlashAttribute("mensagem", mensagem);
			return new ModelAndView("redirect:/usuario/novo");
		}

		mensagem = String.format("O registro Usuário '%d' foi alterado.", usuario.getId());
		return novo(usuario).addObject("mensagem", mensagem);
	}
	
	@GetMapping
	public ModelAndView pesquisar(UsuarioFilter usuarioFilter, @PageableDefault(size = 2) Pageable pageable, HttpServletRequest httpServletRequest) {
		ModelAndView mv = new ModelAndView("usuario/listar-usuario");
		PageWrapper<Usuario> paginaWrapper = new PageWrapper<Usuario>(usuarioRepository.filtrar(usuarioFilter, pageable), httpServletRequest);
		mv.addObject("pagina", paginaWrapper);
		
		return mv;
	}
	
	@GetMapping("/{id}")
	public ModelAndView editar(@PathVariable("id") Long id) {
		Optional<Usuario> optional = usuarioRepository.findById(id);
		
		if (optional.isPresent()) {
			Usuario usuario = optional.get();
			ModelAndView mv = novo(usuario);
			mv.addObject(usuario);
			return mv;
		}
		
		return null;
	}

}