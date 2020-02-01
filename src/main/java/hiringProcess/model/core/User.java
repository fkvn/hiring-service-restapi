package hiringProcess.model.core;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "user")
public class User implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Integer id;

	@Column(name = "first_name", nullable = false)
	@NotNull(message = "firstName cannot be null")
	private String firstName;
	
	@Column(name = "middle_name")
	private String middleName;
	
	@Column(name = "last_name", nullable = false)
	@NotNull(message = "lastName cannot be null")
	private String lastName;

	@JsonIgnore
	@Column(nullable = false)
	@NotNull(message = "password cannot be null")
	private String password;

	@Column(nullable = false, unique = true)
	@NotNull(message = "Username cannot be null")
	private String username;

	@Column(unique = true, nullable = false)
	@NotNull(message = "email cannot be null")
	private String email;

	private String phone;

	@ElementCollection
	@CollectionTable(name = "authorities", joinColumns = @JoinColumn(name = "user_id"))
	@Column(name = "role")
	private Set<String> roles;

	@Column(nullable = false)
	@JsonProperty("active")
	private boolean enabled;

	public User() {

		enabled = true;
		roles = new HashSet<String>();
	}

	
	public Integer getId() {
	
		return id;
	}

	
	public void setId(Integer id) {
	
		this.id = id;
	}

	
	public String getFirstName() {
	
		return firstName;
	}

	
	public void setFirstName(String firstName) {
	
		this.firstName = firstName;
	}

	
	public String getLastName() {
	
		return lastName;
	}

	
	public void setLastName(String lastName) {
	
		this.lastName = lastName;
	}

	
	public String getMiddleName() {
	
		return middleName;
	}

	
	public void setMiddleName(String middleName) {
	
		this.middleName = middleName;
	}

	
	public String getPassword() {
	
		return password;
	}

	
	public void setPassword(String password) {
	
		this.password = password;
	}

	
	public String getUsername() {
	
		return username;
	}

	
	public void setUsername(String username) {
	
		this.username = username;
	}

	
	public String getEmail() {
	
		return email;
	}

	
	public void setEmail(String email) {
	
		this.email = email;
	}

	
	public String getPhone() {
	
		return phone;
	}

	
	public void setPhone(String phone) {
	
		this.phone = phone;
	}

	
	public Set<String> getRoles() {
	
		return roles;
	}

	
	public void setRoles(Set<String> roles) {
	
		this.roles = roles;
	}

	
	public boolean isEnabled() {
	
		return enabled;
	}

	
	public void setEnabled(boolean enabled) {
	
		this.enabled = enabled;
	}
	
	

}
