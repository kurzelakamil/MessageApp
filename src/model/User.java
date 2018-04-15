package model;

import org.mindrot.jbcrypt.BCrypt;

public class User {
	Integer id = 0;
	String username;
	String password;
	
	public User(String username, String password) {
		this.username = username;
		this.setPassword(password);
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public Integer getId() {
		return id;
	}
	
	public void setPassword(String password) {
		this.password = BCrypt.hashpw(password, BCrypt.gensalt());
	}
}