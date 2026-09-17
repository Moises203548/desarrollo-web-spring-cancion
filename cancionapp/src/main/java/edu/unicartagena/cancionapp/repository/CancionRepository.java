package edu.unicartagena.cancionapp.repository;

import edu.unicartagena.cancionapp.model.Cancion;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface CancionRepository extends JpaRepository<Cancion, Long> {
    List<Cancion> findByBandaContainingIgnoreCase(String banda);
    List<Cancion> findByAlbumContainingIgnoreCase(String album);
}