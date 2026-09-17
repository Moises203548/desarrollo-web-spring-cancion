package edu.unicartagena.cancionapp.controller;

import edu.unicartagena.cancionapp.model.Usuario;
import edu.unicartagena.cancionapp.service.EmailService;
import edu.unicartagena.cancionapp.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import jakarta.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/recuperar-clave")
public class RecuperarClaveController {

    @Autowired private UsuarioService usuarioService;
    @Autowired private EmailService emailService;
    @Autowired private PasswordEncoder passwordEncoder;

    @GetMapping
    public String mostrarFormularioSolicitud() {
        return "auth/recuperar-clave";
    }

    @PostMapping
    public String solicitarRecuperacion(@RequestParam String email, HttpServletRequest request, Model model) {
        String token = usuarioService.generarTokenRecuperacion(email);
        if (token != null) {
            String enlace = request.getRequestURL().toString().replace("/recuperar-clave", "")
                    + "/recuperar-clave/restablecer?token=" + token;
            emailService.enviarCorreoRecuperacion(email, enlace);
        }
        model.addAttribute("mensaje", "Si el correo existe, se envió un enlace de recuperación.");
        return "auth/recuperar-clave";
    }

    @GetMapping("/restablecer")
    public String mostrarFormularioRestablecer(@RequestParam String token, Model model) {
        model.addAttribute("token", token);
        return "auth/restablecer-clave";
    }

    @PostMapping("/restablecer")
    public String restablecerClave(@RequestParam String token, @RequestParam String nuevaClave, Model model) {
        Usuario usuario = usuarioService.buscarPorToken(token).orElse(null);
        if (usuario == null) {
            model.addAttribute("error", "Token inválido o expirado.");
            return "auth/restablecer-clave";
        }
        usuarioService.restablecerClave(usuario, passwordEncoder.encode(nuevaClave));
        return "redirect:/login?claveActualizada";
    }
}