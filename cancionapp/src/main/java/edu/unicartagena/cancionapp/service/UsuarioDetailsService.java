package edu.unicartagena.cancionapp.service;

import edu.unicartagena.cancionapp.model.Usuario;
import edu.unicartagena.cancionapp.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UsuarioDetailsService implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String nombre) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findByNombre(nombre)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado: " + nombre));

        return User.builder()
                .username(usuario.getNombre())
                .password(usuario.getClave())
                .authorities(new SimpleGrantedAuthority("ROLE_" + usuario.getRol().toUpperCase()))
                .build();
    }
}