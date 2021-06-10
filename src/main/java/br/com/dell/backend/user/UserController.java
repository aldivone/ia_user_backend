package br.com.dell.backend.user;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping(value = "v1/users")
public class UserController {

	@Autowired
	private UserRepository repository;

	@GetMapping
	public ResponseEntity<List<UserVO>> getAll() {
		List<User> users = repository.findAll();
		return ResponseEntity.ok(users.stream().map(User::toUserVO).collect(Collectors.toList()));
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<UserVO> getFindById(@PathVariable() Long id) {
		Optional<User> user = repository.findById(id);
		if (user.isPresent())
			return ResponseEntity.ok(User.toUserVO(user.get()));
		else
			return ResponseEntity.notFound().build();
	}

	@PostMapping
	public ResponseEntity<URI> insert(@RequestBody UserVO userVO) {
		var createdUser = User.toUserVO(repository.save(UserVO.toUser(userVO)));
		var uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(createdUser.getId())
				.toUri();
		return ResponseEntity.created(uri).build();
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<UserVO> update(@RequestBody UserVO userVO, @PathVariable() Long id) {
		if (!repository.findById(id).isPresent()) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(User.toUserVO(repository.save(UserVO.toUser(userVO))));
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> remove(@PathVariable() Long id) {
		if (!repository.findById(id).isPresent()) {
			return ResponseEntity.noContent().build();
		}
		repository.deleteById(id);
		return ResponseEntity.noContent().build();
	}

}
