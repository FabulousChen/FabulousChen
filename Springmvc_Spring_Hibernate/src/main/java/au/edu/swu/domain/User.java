package au.edu.swu.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name="User")
public class User implements Serializable{
	private int id;
	private String password;
	private String username;
	
	@Id
	@Column(name="ID")
	@GeneratedValue
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	@Column(name="password")
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Column(name="username")
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", password=" + password + ", username=" + username + "]";
	}
	public User(int id, String password, String username) {
		super();
		this.id = id;
		this.password = password;
		this.username = username;
	}
	public User() {
		super();
	}
	
	
	
}
