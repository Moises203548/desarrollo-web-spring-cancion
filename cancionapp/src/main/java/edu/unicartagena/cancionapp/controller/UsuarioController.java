package edu.unicartagena.cancionapp.controller;

import edu.unicartagena.cancionapp.model.Usuario;
import edu.unicartagena.cancionapp.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("usuarios", usuarioService.listarTodos());
        return "usuarios/lista";
    }

    @GetMapping("/{id}")
    public String verDetalle(@PathVariable Long id, Model model) {
        model.addAttribute("usuario", usuarioService.buscarPorId(id).orElse(null));
        return "usuarios/detalle";
    }

    @GetMapping("/nuevo")
    public String mostrarFormularioNuevo(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "usuarios/formulario";
    }

    @GetMapping("/{id}/editar")
    public String mostrarFormularioEditar(@PathVariable Long id, Model model) {
        model.addAttribute("usuario", usuarioService.buscarPorId(id).orElse(new Usuario()));
        return "usuarios/formulario";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute Usuario usuario) {
        usuarioService.guardar(usuario);
        return "redirect:/usuarios";
    }

    @GetMapping("/{id}/eliminar")
    public String eliminar(@PathVariable Long id) {
        usuarioService.eliminar(id);
        return "redirect:/usuarios";
    }
    @GetMapping("/reportes/por-rol")
    public String reportePorRol(@RequestParam(required = false) String rol, Model model) {
        if (rol != null && !rol.isBlank()) {
            model.addAttribute("usuarios", usuarioService.buscarPorRol(rol));
        }
        model.addAttribute("rolBuscado", rol);
        return "usuarios/reporte-rol";
    }

    @GetMapping("/reportes/por-nombre")
    public String reportePorNombre(@RequestParam(required = false) String nombre, Model model) {
        if (nombre != null && !nombre.isBlank()) {
            model.addAttribute("usuarios", usuarioService.buscarPorNombreParcial(nombre));
        }
        model.addAttribute("nombreBuscado", nombre);
        return "usuarios/reporte-nombre";
    }
}