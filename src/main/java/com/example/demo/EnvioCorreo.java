package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EnvioCorreo {
@Autowired
    private JavaMailSender EmailServer;

public void enviarEmail (String destinatario , Visitante visitante){
    SimpleMailMessage mensaje = new SimpleMailMessage();
    mensaje.setTo(destinatario);
    mensaje.setSubject("Nuevo visitante registrado");
    mensaje.setText("Se ha registrado un nuevo visitante:\n\n" +
            "Nombre: " + visitante.getNombreApellidos() + "\n" +
            "Cédula: " + visitante.getCedula() + "\n" +
            "Fecha de ingreso: " + visitante.getFechaIngresoPuesto() + "\n" +
            "Empresa: " + visitante.getEmpresaGestionaPuesto());
    EmailServer.send(mensaje);
}

}
