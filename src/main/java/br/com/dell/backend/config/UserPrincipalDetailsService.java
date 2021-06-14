package br.com.dell.backend.config;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.dell.backend.user.User;
import br.com.dell.backend.user.UserRepository;

@Service
public class UserPrincipalDetailsService implements UserDetailsService {

	private UserRepository repository;

	public UserPrincipalDetailsService(UserRepository repository) {
		super();
		this.repository = repository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return User.toUserVO(repository.findByLogin(username));
	}

}
