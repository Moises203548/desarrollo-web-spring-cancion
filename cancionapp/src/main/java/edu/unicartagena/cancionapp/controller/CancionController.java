package edu.unicartagena.cancionapp.controller;

import edu.unicartagena.cancionapp.model.Cancion;
import edu.unicartagena.cancionapp.service.CancionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/canciones")
public class CancionController {

    @Autowired
    private CancionService cancionService;

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("canciones", cancionService.listarTodas());
        return "canciones/lista";
    }

    @GetMapping("/{id}")
    public String verDetalle(@PathVariable Long id, Model model) {
        model.addAttribute("cancion", cancionService.buscarPorId(id).orElse(null));
        return "canciones/detalle";
    }
}