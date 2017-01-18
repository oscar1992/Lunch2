/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.lunch.correos;

import co.com.lunch.logic.cliente.PadreLogic;
import co.com.lunch.persistencia.cliente.PadreEntity;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author oscarramirez
 */
public class EnviaCorreo2 {

    public boolean envia(String email) {
        String para = email;
        String correo = null;
        GeneraContrasena gene = new GeneraContrasena();
        correo = gene.generaContrasena(email);
        try (PadreLogic padre = new PadreLogic()) {
            PadreEntity padreE = padre.padrePorEmail(email);
            padreE.setContrasena(correo);
            padre.actualizaPadre(padreE);
        } catch (Exception e) {
            System.out.println("Error cambiando la contraseña");
        }
        // La dirección de la cuenta de envío (from)
        String de = "La Lonchera";

        // El servidor (host). En este caso usamos localhost
        String host = "localhost";

        // Obtenemos las propiedades del sistema
        Properties propiedades = System.getProperties();

        // Configuramos el servidor de correo
        propiedades.put("mail.smtp.auth", "true");
        propiedades.put("mail.smtp.starttls.enable", "true");
        propiedades.put("mail.smtp.host", "smtp.gmail.com");
        propiedades.put("mail.smtp.port", "587");

        // Obtenemos la sesión por defecto
        //Session sesion = Session.getDefaultInstance(propiedades);
        Session sesion = Session.getInstance(propiedades,
                new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("laloncheraparati@gmail.com", "rqeasqirhfvbyfcs");
            }
        });

        try {
            // Creamos un objeto mensaje tipo MimeMessage por defecto.
            MimeMessage mensaje = new MimeMessage(sesion);

            // Asignamos el “de o from” al header del correo.
            mensaje.setFrom(new InternetAddress(de));

            // Asignamos el “para o to” al header del correo.
            //mensaje.addRecipient(Message.RecipientType.TO, new InternetAddress("katana092@gmail.com"));
            mensaje.addRecipient(Message.RecipientType.TO, new InternetAddress(email));
            // Asignamos el asunto
            mensaje.setSubject("Reestablecer Contraseña");

            // Asignamos el mensaje como tal
            mensaje.setText("Hola! Solicitaste una nueva contrasena. Tu nueva contrasena es: " + correo);

            // Enviamos el correo
            Transport.send(mensaje);
            System.out.println("Mensaje enviado: " + mensaje.getFileName());
            return true;
        } catch (MessagingException e) {
            e.printStackTrace();
            return false;
        }
    }
}
