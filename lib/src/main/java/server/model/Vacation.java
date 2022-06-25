package server.model;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Formula;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "vacations")
public class Vacation implements Comparable<Vacation>{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Size(min = 0, max = 50)
	@Column(unique = true)
	private String title;
	
	 
	@Size(min = 0, max = 50)
	@Column
	private String destination;
	
	@Lob
	@Column
	private String description;

    @OneToMany(
            mappedBy = "vacation",
            cascade = CascadeType.ALL,
            orphanRemoval = true
        )
	private Set<VacationPriority> vacationPriorities =  new HashSet<>();

	@Formula("(select sum(vp.priority) from vacation_priorities vp where vp.vacation_id = id group by vp.vacation_id)")
	private int totalPriority;
	
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
	
	@JsonIgnore
	public Set<VacationPriority> getPrioritizers () {
		return vacationPriorities;
	}
	
	public int getPriority() {
		return totalPriority;
	}
	
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vacation vacation = (Vacation) o;
        return Objects.equals(title, vacation.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title);
    }

	@Override
	public int compareTo(Vacation o) {
		return Integer.compare(getPriority(), o.getPriority());
	}
}
