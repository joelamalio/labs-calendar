package br.com.joelamalio.calendar.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import br.com.joelamalio.calendar.domain.Usuario;
import br.com.joelamalio.calendar.exception.RegistroDuplicadoException;
import br.com.joelamalio.calendar.repository.UsuarioRepository;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Transactional
	public Usuario salvar(Usuario usuario) {
		Optional<Usuario> optional = usuarioRepository.validarDuplicidade(usuario);
		if (optional.isPresent() && !optional.get().equals(usuario)) {
			throw new RegistroDuplicadoException("Login já cadastrado");
		}
		
		if (usuario.isNovo() && StringUtils.isEmpty(usuario.getSenha())) {
			throw new RuntimeException("O campo Senha é obrigatório");
		}
		
		if (usuario.isNovo() || !StringUtils.isEmpty(usuario.getSenha())) {
			String senhaCriptografada = this.passwordEncoder.encode(usuario.getSenha());
			usuario.setSenha(senhaCriptografada);
		} else if (StringUtils.isEmpty(usuario.getSenha())) {
			usuario.setSenha(optional.get().getSenha());
		}
		usuario.setConfirmacaoSenha(usuario.getSenha());
		
		return usuarioRepository.saveAndFlush(usuario);
	}

}
