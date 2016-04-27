package co.com.mobick.modelos;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.util.Properties;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author Seba
 */
public class Email {

    //public boolean enviarCorreo(String de, String clave, String[] para, String mensaje, String asunto){
    public boolean enviarCorreo(Empleado empleado, UsuarioLogin usuario, String nuevaContrasenia) {
        boolean enviado = false;
        System.out.println("ENTRE A ENVIAR EL CORREO :D");
        String de = "appmobick@hotmail.com";
        String clave = "Mobick123456";
        String asunto = "Recuperacion contraseña -- Mobick";
        String para = empleado.getCorreo();
        String mensaje = "Hemos recibido la solicitud para recuperar tu contraseña: \n \n \n"
                + " Para ingresar al sistema tu usuario es : " + usuario.getUsuario() + "\n"
                + " Y tu contraseña es : " + nuevaContrasenia;
        try {

            String host = "smtp-mail.outlook.com";

            Properties prop = System.getProperties();

            prop.put("mail.smtp.starttls.enable", "true");
            prop.put("mail.smtp.host", host);
            prop.put("mail.smtp.user", de);
            prop.put("mail.smtp.password", clave);
            prop.put("mail.smtp.port", 587);
            prop.put("mail.smtp.auth", "true");

            Session sesion = Session.getDefaultInstance(prop, null);

            MimeMessage message = new MimeMessage(sesion);

            message.setFrom(new InternetAddress(de));

            /*
                    
             NOTA: para enviar correo electronico masivo
                
             InternetAddress[] direcciones = new InternetAddress[para.length];
             for(int i=0;i<para.length;i++){
             direcciones[i] = new InternetAddress(para[i]);
             }
                
             for(int i=0;i<direcciones.length;i++){
             message.addRecipient(Message.RecipientType.TO, direcciones[i]);
             }
                
             */
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(para));

            message.setSubject(asunto);
            message.setText(mensaje);

            Transport transport = sesion.getTransport("smtp");

            transport.connect(host, de, clave);

            transport.sendMessage(message, message.getAllRecipients());

            transport.close();

            enviado = true;
        } catch (Exception e) {
            System.out.println("correo");
            e.printStackTrace();
        }

        return enviado;
    }

}
