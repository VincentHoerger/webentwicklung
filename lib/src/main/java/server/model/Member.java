package server.model;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Objects;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import io.swagger.v3.oas.annotations.media.Schema;

@EnableAutoConfiguration

@Entity
@Table(name = "members")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,property = "id")
public class Member { 

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Schema(accessMode = Schema.AccessMode.READ_ONLY)
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
	
	@OneToMany(
			mappedBy = "member",
			cascade = CascadeType.ALL,
			orphanRemoval = true
			)
	private Set<VacationPriority> priorities = new HashSet<>();
	
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
	
	public Set<VacationPriority> getPriorities () {
		return priorities;
	}
	
	// Sollte eigentlich mit getReferenceById f�r Vacation funktionieren. Tut es aber nicht, keine Ahnung warum? Composite Key?
	// Fehlermeldung NonUniqueObjectException: a different object with the same identifier value was already associated with the session VacationPriority
	public void addPriority (Vacation vacation, int priority) {
		VacationPriority vacationPriority = new VacationPriority(this, vacation, priority);
		this.priorities.add(vacationPriority);
		vacation.getPrioritizers().add(vacationPriority);
	}
	
	public void removePriority (long vacationId) {
		for (Iterator<VacationPriority> iterator = priorities.iterator(); iterator.hasNext(); ) {
			VacationPriority vacationPriority = iterator.next();
			if (vacationPriority.getMember().equals(this) && vacationPriority.getVacation().getId() == vacationId) {
				iterator.remove();
				vacationPriority.getVacation().getPrioritizers().remove(vacationPriority);
				vacationPriority.setMember(null);
				vacationPriority.setVacation(null);
			}
		}
	}
	
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Member member = (Member) o;
        return Objects.equals(username, member.username);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username);
    }
}
