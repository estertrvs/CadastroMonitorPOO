package cadastroMonitores;


import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Mensageiro {
	public static void enviarEmail(String email, String assunto, String mensagemInscricao) throws Exception {
	    String emailRemetente = "testepoo99@gmail.com";
	    String senhaRemetente = "zyrq smzz atgf ajwx";
	    String emailDestinatario = email;
	
	    Properties props = new Properties();
	    props.put("mail.smtp.auth", "true");
	    props.put("mail.smtp.starttls.enable", "true");
	    props.put("mail.smtp.host", "smtp.gmail.com");
	    props.put("mail.smtp.port", "587");
	    props.put("mail.smtp.socketFactory.port", "465");
	    props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
	
	    Session session = Session.getInstance(props, new Authenticator() {
	        protected PasswordAuthentication getPasswordAuthentication() {
	            return new PasswordAuthentication(emailRemetente, senhaRemetente);
	        }});
	    
        Message mensagem = new MimeMessage(session);
        mensagem.setFrom(new InternetAddress(emailRemetente));
        mensagem.setRecipient(Message.RecipientType.TO, new InternetAddress(emailDestinatario));
        mensagem.setSubject(assunto);
        mensagem.setText(mensagemInscricao);

        Transport.send(mensagem);  
	}
}
