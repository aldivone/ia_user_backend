package br.com.dell.backend.user;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserControllerTests {

	@LocalServerPort
	private int port;

	private String url;

	@Autowired
	private TestRestTemplate restTemplate;

	@BeforeEach
	public void setUp() {
		url = String.format("http://localhost:%d/api/v1/users", port);
	}

	@Test
	public void getUserAll() {
		var hasBody = this.restTemplate
				.exchange(url, HttpMethod.GET, null, new ParameterizedTypeReference<List<UserVO>>() {
				}).getBody();
		assertThat(hasBody).asList().size().isEqualTo(1);
	}

}
