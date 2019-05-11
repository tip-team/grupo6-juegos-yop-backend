package ar.edu.unq.tip.grupo6.app.service;

import static java.util.Collections.emptyList;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import ar.edu.unq.tip.grupo6.app.model.Usuario;
import ar.edu.unq.tip.grupo6.app.repository.UsuarioRepository;
import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class UsuarioDetailsServiceImpl implements UserDetailsService {

	private UsuarioRepository usuarioRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Usuario usuario = usuarioRepository.findByUsername(username);
		if (usuario == null) {
			throw new UsernameNotFoundException(username);
		}
		return new User(usuario.getUsername(), usuario.getPassword(), emptyList());
	}
	
}
