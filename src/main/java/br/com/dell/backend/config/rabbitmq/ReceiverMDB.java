package br.com.dell.backend.config.rabbitmq;

import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import br.com.dell.backend.email.Email;

@Component
public class ReceiverMDB {

	Logger logger = Logger.getLogger(ReceiverMDB.class.getCanonicalName());

	@Autowired
	private JavaMailSender mailSender;

	public void receiveMessage(Email email) {

		try {
			var mail = mailSender.createMimeMessage();
			var helper = new MimeMessageHelper(mail);
			helper.setTo(email.getDestinatario());
			helper.setSubject("Mensagem do usuarioApp");
			helper.setText(email.getConteudo(), true);
			mailSender.send(mail);
			logger.info("Email enviado com sucesso!");
		} catch (Exception e) {
			logger.info("Problema no envio do e-mail. Detalhes: " + e.getMessage());
		}
	}

	@Bean
	public JavaMailSender getJavaMailSender() {
		var mailSenderBean = new JavaMailSenderImpl();
		mailSenderBean.setHost("smtp.gmail.com");
		mailSenderBean.setPort(465);

		mailSenderBean.setUsername("altanticoteste@gmail.com");
		mailSenderBean.setPassword("qazxsw@425");

		var props = mailSenderBean.getJavaMailProperties();
		props.put("mail.transport.protocol", "smtp");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.starttls.required", "true");
		props.put("mail.smtp.ssl.enable", true);
		props.put("mail.test-connection", true);
		props.put("mail.debug", false);

		return mailSenderBean;
	}

}
