package br.com.dell.backend.email;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;

import br.com.dell.backend.user.User;
import br.com.dell.backend.user.UserRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "Enviar um email, passando a mensagem como parâmetro usando o Kafka", tags = "Enviar um email, passando a mensagem como parâmetro usando o Kafka")
@RestController
@RequestMapping(value = "v1/kafka-email")
public class EmailKafkaController {

	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;

	@Autowired
	private UserRepository repository;

	@GetMapping(value = "/admin")
	@ApiOperation(value = "Enviar e-mail para todos os usuários administradores")
	public void sendEmail() {
		List<User> findAll = repository.findAll();
		if (!findAll.isEmpty()) {
			List<User> adminUsers = findAll.stream().filter(User::isAdmin).collect(Collectors.toList());
			if (!adminUsers.isEmpty()) {
				adminUsers.forEach(user -> kafkaTemplate.send("fila-email-consume", new Gson().toJson(new Email(
						"<p>Você está recebendo o e-mail que por que é administrador do sistema.</p><p>Nome do user: "
								+ user.getName() + "</p>",
						user.getEmail()))));
			}
		}
	}

	@GetMapping()
	@ApiOperation(value = "Enviar e-mail para um login especifico")
	public void sendEmailForLogin(@RequestParam String login) {
		User findByLogin = repository.findByLogin(login);
		if (findByLogin != null) {
			kafkaTemplate.send("fila-email-consume",
					new Gson().toJson(
							new Email("<p>O e-mail foi enviado especificamente para sua pessoa.</p><p>Nome do user: "
									+ findByLogin.getName() + "</p>", findByLogin.getEmail())));
		}
	}

}
