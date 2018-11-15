package br.com.joelamalio.calendar.security;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.joelamalio.calendar.domain.Usuario;
import br.com.joelamalio.calendar.repository.AutorizacaoRepository;
import br.com.joelamalio.calendar.repository.UsuarioRepository;

@Service
public class UsuarioDetailsService implements UserDetailsService {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private AutorizacaoRepository autorizacaoRepository;
	
	@Override
	public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
		Optional<Usuario> optional = usuarioRepository.logar(login);
		Usuario usuario = optional.orElseThrow(() -> new UsernameNotFoundException("Usuário e/ou senha estão incorretos"));
		
		return new UsuarioSecurity(usuario, obterAutorizacoes(usuario));
	}
	
	private Collection<? extends GrantedAuthority> obterAutorizacoes(Usuario usuario) {
		Set<SimpleGrantedAuthority> authorities = new HashSet<SimpleGrantedAuthority>() ;
		List<String> autorizacoes = autorizacaoRepository.obter(usuario);
		
		autorizacoes.forEach(p -> authorities.add(new SimpleGrantedAuthority(p.toUpperCase())));
		
		return authorities;
	}

}
