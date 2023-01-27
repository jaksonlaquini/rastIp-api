package br.com.rastip.core;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Component;

import br.com.rastip.model.Mensagem;

@Component
public class Mail {

	@Autowired
	private JavaMailSender javaMailSender;
	
	public void enviar(Mensagem mensagem) {
	
		MimeMessagePreparator messagePreparator = mimeMessage -> {
	        MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
	        messageHelper.setFrom(mensagem.getRemetente());
	        messageHelper.setTo(mensagem.getDestinatario());
	        messageHelper.setSubject(mensagem.getAssunto());
	        String content = mensagem.getCorpo();
	        messageHelper.setText(content, true);
	    };
	    try {
	    	javaMailSender.send(messagePreparator);
	    } catch (org.springframework.mail.MailException e) {
	        // runtime exception; compiler will not force you to handle it
	    }
	}


}
