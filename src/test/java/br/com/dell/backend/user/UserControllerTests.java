package br.com.dell.backend.user;

import static org.assertj.core.api.Assertions.assertThat;

import java.net.URI;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@TestMethodOrder(OrderAnnotation.class)
@SuppressWarnings("squid:S2259")
public class UserControllerTests {

	private static final String PASSWORD = "qazxsw123";

	private static final String USER = "admin";

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
	@Order(1)
	public void insert() {
		var userVO = new UserVO(null, "Aldivone Correia", "aldivone", "qazxsw#123", null, null, "aldivone@gmail.com",
				false);
		var hasBody = this.restTemplate.withBasicAuth(USER, PASSWORD).postForEntity(url, userVO, URI.class);
		var actual = hasBody.getHeaders().getLocation();
		assertThat(actual).isNotNull();
		assertThat(actual.toString()).isEqualTo("http://localhost:8080/api/v1/users/1");
		assertThat(hasBody.getStatusCodeValue()).isEqualTo(201);
	}

	@Test
	@Order(2)
	public void getUserAll() {
		var hasBody = this.restTemplate.withBasicAuth(USER, PASSWORD)
				.exchange(url, HttpMethod.GET, null, new ParameterizedTypeReference<List<UserVO>>() {
				}).getBody();
		assertThat(hasBody).asList().size().isEqualTo(1);
	}

	@Test
	@Order(3)
	public void getFindById() {
		var userVO = this.restTemplate.withBasicAuth(USER, PASSWORD)
				.exchange(url + "/1", HttpMethod.GET, null, new ParameterizedTypeReference<UserVO>() {
				}).getBody();
		assertThat(userVO).isNotNull();
		assertThat(userVO.getName()).isEqualTo("Aldivone Correia");
	}

	@Test
	@Order(4)
	public void update() {
		var userVO = this.restTemplate.withBasicAuth(USER, PASSWORD)
				.exchange(url + "/1", HttpMethod.GET, null, new ParameterizedTypeReference<UserVO>() {
				}).getBody();
		userVO.setEmail("aldivone@hotmail.com");

		this.restTemplate.withBasicAuth(USER, PASSWORD).exchange(url + "/1", HttpMethod.PUT,
				new HttpEntity<UserVO>(userVO), new ParameterizedTypeReference<UserVO>() {
				}).getBody();

		var userVOUpdated = this.restTemplate.withBasicAuth(USER, PASSWORD)
				.exchange(url + "/1", HttpMethod.GET, null, new ParameterizedTypeReference<UserVO>() {
				}).getBody();

		assertThat(userVOUpdated).isNotNull();
		assertThat(userVOUpdated.getUpdatedDate()).isNotNull();
		assertThat(userVO.getEmail()).isEqualTo("aldivone@hotmail.com");
	}

	@Test
	@Order(5)
	public void remove() {
		this.restTemplate.withBasicAuth(USER, PASSWORD).exchange(url + "/1", HttpMethod.DELETE, null,
				ResponseEntity.class);

		var userRemove = this.restTemplate.withBasicAuth(USER, PASSWORD)
				.exchange(url + "/1", HttpMethod.GET, null, new ParameterizedTypeReference<UserVO>() {
				}).getBody();

		assertThat(userRemove).isNull();
	}

}
