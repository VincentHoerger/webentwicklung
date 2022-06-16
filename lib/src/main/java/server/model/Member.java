package server.model;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.Size;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.AccessMode;

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
	
//	@ManyToMany
//	@JoinTable(name = "vacation_priorities",
//			joinColumns = @JoinColumn(name = "member_id"), 
//			inverseJoinColumns = @JoinColumn(name = "vacation_id")
//	 )
//	@ArraySchema(
//			schema = @Schema(
//				implementation = String.class,
//				accessMode = AccessMode.WRITE_ONLY
//		))
//	@JsonIgnore
//	private Set<Vacation> prioritizedVacations = new HashSet<>();;
	
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
	
//	public Set<Vacation> getPriorities () {
//		return prioritizedVacations;
//	}
//	
//	public void addPrioritizer (Vacation vacation) {
//		this.prioritizedVacations.add(vacation);
//		vacation.getPrioritizers().add(this);
//	}
//	
//	public void removePrioritizer (long vacationId) {
//		Vacation vacation = this.prioritizedVacations.stream().filter(v -> v.getId() == vacationId).findFirst().orElse(null);
//		if  (vacation != null) {
//			this.prioritizedVacations.remove(vacation);
//			vacation.getPrioritizers().remove(this);
//		}
//	}
}
