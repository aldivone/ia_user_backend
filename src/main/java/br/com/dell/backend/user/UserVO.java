package br.com.dell.backend.user;

import java.util.Date;

import javax.validation.constraints.NotEmpty;

public class UserVO {

	private Long id;

	@NotEmpty
	private String name;

	@NotEmpty
	private String login;

	@NotEmpty
	private String password;

	@NotEmpty
	private Date createdDate;

	private Date updatedDate;

	@NotEmpty
	private String email;

	@NotEmpty
	private boolean admin;

	public UserVO() {
		super();
	}

	public UserVO(Long id, @NotEmpty String name, @NotEmpty String login, @NotEmpty String password,
			@NotEmpty Date createdDate, @NotEmpty Date updatedDate, @NotEmpty String email, @NotEmpty boolean admin) {
		super();
		this.id = id;
		this.name = name;
		this.login = login;
		this.password = password;
		this.createdDate = createdDate;
		this.updatedDate = updatedDate;
		this.email = email;
		this.admin = admin;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean isAdmin() {
		return admin;
	}

	public void setAdmin(boolean admin) {
		this.admin = admin;
	}

	public static User toUser(UserVO user) {
		return new User(user.getId(), user.getName(), user.getLogin(), user.getPassword(), user.getCreatedDate(),
				user.getUpdatedDate(), user.getEmail(), user.isAdmin());
	}

	@Override
	public int hashCode() {
		var prime = 31;
		var result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((login == null) ? 0 : login.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserVO other = (UserVO) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (login == null) {
			if (other.login != null)
				return false;
		} else if (!login.equals(other.login))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", login=" + login + ", password=" + password + ", createdDate="
				+ createdDate + ", updatedDate=" + updatedDate + ", email=" + email + ", admin=" + admin + "]";
	}

}
