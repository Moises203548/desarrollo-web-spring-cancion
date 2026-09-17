package edu.unicartagena.cancionapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    public void enviarCorreoRecuperacion(String destinatario, String enlace) {
        SimpleMailMessage mensaje = new SimpleMailMessage();
        mensaje.setTo(destinatario);
        mensaje.setSubject("Recuperación de clave - Cancion App");
        mensaje.setText("Hola,\n\nPara restablecer tu clave, haz clic en el siguiente enlace:\n"
                + enlace + "\n\nSi no solicitaste esto, ignora este correo.");
        mailSender.send(mensaje);
    }
}