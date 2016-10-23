/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.lunch.correos;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author oscarramirez
 */
public class EnviaCorreo {
    private final Properties propiedades= new Properties();
    private String pass;
    private Session sesion;
    
    private void init(){
        propiedades.put("mail.smtp.host", "smtp.gmail.com");
        propiedades.put("mail.smtp.startttl.enable", "true");
        propiedades.put("mail.smtp.port", 25);
        propiedades.put("mail.smtp.mail.sender", "soporte@lalonchera.com");
        propiedades.put("mail.smtp.user", "Soporte");
        propiedades.put("mail.smtp.auth", "true");
        sesion = Session.getDefaultInstance(propiedades);
    }
    
    public void enviar() throws MessagingException{
        init();
        
            MimeMessage mesagge = new MimeMessage(sesion);
            mesagge.setFrom(new InternetAddress((String)propiedades.getProperty("mail.smtp.sender")));
            mesagge.addRecipient(Message.RecipientType.TO, new InternetAddress("katana092@gmail.com"));
            mesagge.setSubject("Recuperación de contraseña");
            mesagge.setText("AOAOAOAOAOA");
            Transport t = sesion.getTransport("smtp");
            t.connect((String)propiedades.get("mail.smtp.user"), "pass");
            t.sendMessage(mesagge, mesagge.getAllRecipients());
            t.close();
        
    }
}
