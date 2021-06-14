package br.com.dell.backend.user;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "Crud para cadastros de usuários", tags = "Crud para cadastras de usuários")
@RestController
@RequestMapping(value = "v1/users")
public class UserController {

	@Autowired
	private UserRepository repository;

	@GetMapping
	@ApiOperation(value = "Listar os usuários cadastrados")
	@CrossOrigin(origins = "http://localhost:4200")
	public ResponseEntity<List<UserVO>> getAll() {
		List<User> users = repository.findAll();
		return ResponseEntity.ok(users.stream().map(User::toUserVO).collect(Collectors.toList()));
	}

	@GetMapping(value = "/{id}")
	@ApiOperation(value = "Buscar um usuário por seu id")
	@CrossOrigin(origins = "http://localhost:4200")
	public ResponseEntity<UserVO> getFindById(@PathVariable() Long id) {
		Optional<User> user = repository.findById(id);
		if (user.isPresent())
			return ResponseEntity.ok(User.toUserVO(user.get()));
		else
			return ResponseEntity.notFound().build();
	}

	@PostMapping
	@ApiOperation(value = "Incluir um usuário no sistema")
	@CrossOrigin(origins = "http://localhost:4200")
	public ResponseEntity<URI> insert(@RequestBody UserVO userVO) {
		var createdUser = User.toUserVO(repository.save(UserVO.toUser(userVO)));
		var uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(createdUser.getId())
				.toUri();
		return ResponseEntity.created(uri).build();
	}

	@PutMapping(value = "/{id}")
	@ApiOperation(value = "Altera os dados de um usuário no sistema")
	@CrossOrigin(origins = "http://localhost:4200")
	public ResponseEntity<?> update(@RequestBody UserVO userVO, @PathVariable() Long id) {
		if (!repository.findById(id).isPresent()) {
			return ResponseEntity.noContent().build();
		}
		var userVOTemp = UserVO.toUser(userVO);
		var userDatabase = repository.findByLogin(userVO.getLogin());
		var userLogin = getLoginUserPrincipal();
		if (!userLogin.isAdmin() && (!userLogin.getLogin().equals(userVOTemp.getLogin())
				&& !userDatabase.getPassword().equals(userVOTemp.getPassword()))) {
			return ResponseEntity.status(422)
					.body("{\"mensagem\":\"Somente usuários admin podem alterar a senha de outros usuários\"}");
		}
		if (!userDatabase.getPassword().equals(userVOTemp.getPassword())) {
			userVOTemp.setPassword(new BCryptPasswordEncoder().encode(userVOTemp.getPassword()));
		}

		return ResponseEntity.ok(User.toUserVO(repository.save(userVOTemp)));
	}

	@DeleteMapping(value = "/{id}")
	@ApiOperation(value = "Remover um usuário do sistema")
	@CrossOrigin(origins = "http://localhost:4200")
	public ResponseEntity<Void> remove(@PathVariable() Long id) {
		if (!repository.findById(id).isPresent()) {
			return ResponseEntity.noContent().build();
		}
		repository.deleteById(id);
		return ResponseEntity.noContent().build();
	}

	@GetMapping(value = "/acesso")
	@ApiOperation(value = "Buscar os dados do usuário autenticando no sistema")
	@CrossOrigin(origins = "http://localhost:4200")
	public ResponseEntity<UserVO> login(@RequestParam String login) {
		return ResponseEntity.ok(User.toUserVO(this.repository.findByLogin(login)));
	}

	public UserVO getLoginUserPrincipal() {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (principal instanceof UserDetails) {
			return (UserVO) principal;
		} else {
			return User.toUserVO(this.repository.findByLogin(principal.toString()));
		}
	}

}
