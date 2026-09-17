package edu.unicartagena.cancionapp.controller;

import edu.unicartagena.cancionapp.model.Usuario;
import edu.unicartagena.cancionapp.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class AuthController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/login")
    public String login() {
        return "auth/login";
    }

    @GetMapping("/registro")
    public String mostrarRegistro(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "auth/registro";
    }

    @PostMapping("/registro")
    public String registrar(@ModelAttribute Usuario usuario) {
        usuario.setClave(passwordEncoder.encode(usuario.getClave()));
        if (usuario.getRol() == null || usuario.getRol().isBlank()) {
            usuario.setRol("USUARIO");
        }
        usuarioService.guardar(usuario);
        return "redirect:/login?registrado";
    }
}