package server.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "vacations")
public class Vacation {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Size(min = 0, max = 50)
	@Column(unique = true)
	private String title;
	
	@Lob 
	@Size(min = 0, max = 50)
	@Column
	private String destination;
	
	@Column
	private String description;

//	@ManyToMany(mappedBy = "prioritizedVacations")
//	//@JsonIgnore
//	private Set<Member> prioritizedBy =  new HashSet<>();;

	public Vacation() {
		
	}
	
	public Vacation(String title, String destination, String description) {
		this.title = title;
		this.destination = destination;
		this.description = description;

	}
	
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	public String getTitle () {
		return title;
	}
	
	public void setTitle (String title) {
		this.title = title;
	}
	
	public String getDestination () {
		return destination;
	}
	
	public void setDestination (String destination) {
		this.destination = destination;
	}
	
	public String getDescription () {
		return description;
	}
	
	public void setDescription (String description) {
		this.description = description;
	}
	
//	public Set<Member> getPrioritizers () {
//		return prioritizedBy;
//	}
//	
//	public void addPrioritizer (Member member) {
//		this.prioritizedBy.add(member);
//	}
//	
//	public void removePrioritizer (Member member) {
//		this.prioritizedBy.remove(member);
//	}
}
