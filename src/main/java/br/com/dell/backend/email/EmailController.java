package br.com.dell.backend.email;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.dell.backend.config.rabbitmq.MessagingRabbitMQ;
import io.swagger.annotations.Api;

@Api(value = "Enviar um email, passando a mensagem como parâmetro usando o RabbitMQ", tags = "Enviar um email, passando a mensagem como parâmetro usando o RabbitMQ")
@RestController
@RequestMapping(value = "v1/email")
public class EmailController {

	private final RabbitTemplate rabbitTemplate;

	public EmailController(RabbitTemplate rabbitTemplate) {
		this.rabbitTemplate = rabbitTemplate;
	}

	@PostMapping()
	public void sendEmail(@RequestBody Email email) {
		rabbitTemplate.convertAndSend(MessagingRabbitMQ.TOPIC_EXCHANGE_NAME, "br.com.altantico", email);
	}

}
