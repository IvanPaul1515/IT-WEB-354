package backend.pixel_picture.servicios;

import backend.pixel_picture.Entidades.EncargoFotografia;
import backend.pixel_picture.Entidades.Usuario;
import org.simplejavamail.api.mailer.Mailer;
import org.simplejavamail.api.email.Email;
import org.simplejavamail.email.EmailBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private Mailer mailer;


    // correo Bienvenida
    public void enviarCorreoBienvenida(Usuario usuario) {
        // Construir el contenido del correo
        String asunto = "Bienvenido a Pixel Pictures";
        String cuerpo = String.format("Hola %s,\n\n"
                        + "Gracias por registrarte en nuestra plataforma. "
                        + "A continuación, encontrarás tus credenciales de acceso:\n\n"
                        + "Nombre de usuario: %s\n"
                        + "Contraseña: %s\n\n"
                        + "Accede a nuestra plataforma a través de este enlace: [Enlace de Acceso]\n\n"
                        + "¡Esperamos que disfrutes de nuestros servicios!\n\n"
                        + "Saludos,\n"
                        + "El equipo de Pixel Pictures",
                usuario.getNombre(), usuario.getUserName(), usuario.getPassword());

        // Construir el email
        Email email = EmailBuilder.startingBlank()
                .from("noreply@insightconnect.me")
                .to(usuario.getCorreo())
                .withSubject(asunto)
                .withPlainText(cuerpo)
                .buildEmail();

        // Enviar el email
        mailer.sendMail(email);
    }


    // correo asignacion de trabajo
    public void enviarCorreoAsignacionTrabajo(EncargoFotografia encargo) {
        // Construir el contenido del correo para asignación de trabajo
        String asunto = "Asignación de Trabajo Pendiente";
        String cuerpo = String.format("Hola %s,\n\n"
                        + "Se te ha asignado un nuevo trabajo:\n\n"
                        + "Descripción del trabajo: %s\n\n"
                        + "Por favor, accede a la plataforma y realiza la asignación correspondiente.\n\n"
                        + "Gracias por tu colaboración.\n\n"
                        + "Saludos,\n"
                        + "El equipo de Pixel Pictures",
                 encargo.getServicio());

        // Construir el email
        Email email = EmailBuilder.startingBlank()
                .from("noreply@insightconnect.me")
//                .to(encargo.getCorreoDelEmpleado()) // Utiliza la dirección de correo del empleado
                .withSubject(asunto)
                .withPlainText(cuerpo)
                .buildEmail();

        // Enviar el email
        mailer.sendMail(email);
    }



    //correo de pago

    public void enviarCorreoResumenTransaccion(Usuario cliente, String resumenTransaccion) {
        // Construir el contenido del correo con el resumen de la transacción
        String asunto = "Resumen de Transacción";
        String cuerpo = String.format("Hola %s,\n\n"
                        + "Gracias por realizar la transacción en nuestra plataforma. A continuación, encontrarás un resumen de la transacción:\n\n"
                        + "%s\n\n"
                        + "Gracias por tu preferencia.\n\n"
                        + "Saludos,\n"
                        + "El equipo de Nuestra Plataforma",
                cliente.getNombre(), resumenTransaccion);

        // Construir el email
        Email email = EmailBuilder.startingBlank()
                .from("noreply@insightconnect.me")
                .to(cliente.getCorreo())
                .withSubject(asunto)
                .withPlainText(cuerpo)
                .buildEmail();

        // Enviar el email
        mailer.sendMail(email);
    }
}

