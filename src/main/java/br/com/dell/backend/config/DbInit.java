package br.com.dell.backend.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

import br.com.dell.backend.user.User;
import br.com.dell.backend.user.UserRepository;

@Service
public class DbInit implements CommandLineRunner {

	private UserRepository repository;

	public DbInit(UserRepository repository) {
		super();
		this.repository = repository;
	}

	@Override
	public void run(String... args) throws Exception {
		this.repository
				.save(new User(null, "Administrador", "admin", "qazxsw123", null, null, "admin@gmail.com", true));
	}

}
