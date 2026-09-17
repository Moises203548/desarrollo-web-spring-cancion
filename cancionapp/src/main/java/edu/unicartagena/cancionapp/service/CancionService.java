package edu.unicartagena.cancionapp.service;

import edu.unicartagena.cancionapp.model.Cancion;
import edu.unicartagena.cancionapp.repository.CancionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CancionService {

    @Autowired
    private CancionRepository cancionRepository;

    public List<Cancion> listarTodas() {
        return cancionRepository.findAll();
    }

    public Optional<Cancion> buscarPorId(Long id) {
        return cancionRepository.findById(id);
    }

    public Cancion guardar(Cancion cancion) {
        return cancionRepository.save(cancion);
    }

    public void eliminar(Long id) {
        cancionRepository.deleteById(id);
    }

    // Reportes parametrizados
    public List<Cancion> buscarPorBanda(String banda) {
        return cancionRepository.findByBandaContainingIgnoreCase(banda);
    }

    public List<Cancion> buscarPorAlbum(String album) {
        return cancionRepository.findByAlbumContainingIgnoreCase(album);
    }
}