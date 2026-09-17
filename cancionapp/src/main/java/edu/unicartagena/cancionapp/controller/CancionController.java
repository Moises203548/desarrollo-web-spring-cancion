package edu.unicartagena.cancionapp.controller;

import edu.unicartagena.cancionapp.model.Cancion;
import edu.unicartagena.cancionapp.service.CancionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
    @GetMapping("/nueva")
    public String mostrarFormularioNueva(Model model) {
        model.addAttribute("cancion", new Cancion());
        return "canciones/formulario";
    }

    @GetMapping("/{id}/editar")
    public String mostrarFormularioEditar(@PathVariable Long id, Model model) {
        model.addAttribute("cancion", cancionService.buscarPorId(id).orElse(new Cancion()));
        return "canciones/formulario";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute Cancion cancion) {
        cancionService.guardar(cancion);
        return "redirect:/canciones";
    }

    @GetMapping("/{id}/eliminar")
    public String eliminar(@PathVariable Long id) {
        cancionService.eliminar(id);
        return "redirect:/canciones";
    }
}