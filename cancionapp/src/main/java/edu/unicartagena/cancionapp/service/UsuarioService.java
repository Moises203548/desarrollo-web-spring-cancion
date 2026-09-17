package edu.unicartagena.cancionapp.service;

import edu.unicartagena.cancionapp.model.Usuario;
import edu.unicartagena.cancionapp.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<Usuario> listarTodos() {
        return usuarioRepository.findAll();
    }

    public Optional<Usuario> buscarPorId(Long id) {
        return usuarioRepository.findById(id);
    }

    public Optional<Usuario> buscarPorNombre(String nombre) {
        return usuarioRepository.findByNombre(nombre);
    }

    public Optional<Usuario> buscarPorEmail(String email) {
        return usuarioRepository.findByEmail(email);
    }

    public Usuario guardar(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    public void eliminar(Long id) {
        usuarioRepository.deleteById(id);
    }

    public String generarTokenRecuperacion(String email) {
        Usuario usuario = usuarioRepository.findByEmail(email).orElse(null);
        if (usuario == null) return null;

        String token = UUID.randomUUID().toString();
        usuario.setTokenRecuperacion(token);
        usuarioRepository.save(usuario);
        return token;
    }

    public Optional<Usuario> buscarPorToken(String token) {
        return usuarioRepository.findByTokenRecuperacion(token);
    }

    public void restablecerClave(Usuario usuario, String nuevaClaveEncriptada) {
        usuario.setClave(nuevaClaveEncriptada);
        usuario.setTokenRecuperacion(null);
        usuarioRepository.save(usuario);
    }
}