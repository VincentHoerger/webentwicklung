package server.model;

import java.time.LocalDate;

import javax.validation.constraints.Size;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.format.annotation.DateTimeFormat;

@EnableAutoConfiguration

@Entity
@Table(name = "members")
public class Member { 

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Size(min = 0, max = 50)
	@Column(unique = true)
	private String username;
	
	@Size(min = 0, max = 50)
	@Column
	private String firstname;

	@Size(min = 0, max = 50)
	@Column 
	private String lastname;
	
	@DateTimeFormat
	@Column
	private LocalDate dateOfBirth;
	
	public Member() {
		
	}

	public Member(String username, String firstname, String lastname, LocalDate dateOfBirth) {
		this.username = username;
		this.firstname = firstname;
		this.lastname = lastname;
		this.dateOfBirth = dateOfBirth;
	}
	
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	
	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	
}
