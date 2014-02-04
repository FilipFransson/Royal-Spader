package se.royalspades.model;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;;


@Entity
@Table(name = "users", catalog = "spade_db")
public class User implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private int id;
	private String firstName;
	private String lastName;
	private String role;
	private String email;
	private String username;
	private String password;
	private String salt;
	
	public User(){
		
	}
	
	public User(String firstName, String lastName, String role, String email, String username,
			String password, String salt) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.role = role;
		this.email = email;
		this.username = username;
		this.password = password;
		this.salt = salt;
	}
	
	@Id
    @GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
    @Column(name = "first_name", length = 45)
	public String getFirstName() {
		return firstName;
	}
    
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
    @Column(name = "last_name", length = 45)
	public String getLastName() {
		return lastName;
	}
    
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
    @Column(name = "role", length = 45)
	public String getRole() {
		return role;
	}
    
	public void setRole(String role) {
		this.role = role;
	}
	
    @Column(name = "email", length = 45)
	public String getEmail() {
		return email;
	}
    
	public void setEmail(String email) {
		this.email = email;
	}
	
	@JsonIgnore
    @Column(name = "username", length = 45)
	public String getUsername() {
		return username;
	}
    
	public void setUsername(String username) {
		this.username = username;
	}
	
    @Column(name = "password", length = 45)
    @JsonIgnore 
	public String getPassword() {
		return password;
	}
    
	public void setPassword(String password) {
		this.password = password;
	}
	
	@JsonIgnore
    @Column(name = "salt", length = 45)
	public String getSalt() {
		return salt;
	}
	public void setSalt(String salt) {
		this.salt = salt;
	}
}