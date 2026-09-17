package edu.unicartagena.cancionapp.repository;

import edu.unicartagena.cancionapp.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByNombre(String nombre);
    Optional<Usuario> findByEmail(String email);
    Optional<Usuario> findByTokenRecuperacion(String token);
    List<Usuario> findByRolIgnoreCase(String rol);
    List<Usuario> findByNombreContainingIgnoreCase(String nombre);
}