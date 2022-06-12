package server.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import io.swagger.v3.oas.annotations.media.Schema;

@Entity
@Table(name = "holidays")
public class Holiday {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Size(min = 0, max = 50)
	@Schema(example = "Weihnachtsferien")
	@Column(unique = true)
	private String title;
	
	@DateTimeFormat
	@Schema(example = "2020-12-23")
	@Column
	private LocalDate startDate;
	
	@DateTimeFormat
	@Schema(example = "2021-01-09")
	@Column
	private LocalDate endDate;
	
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Vacation> vacations = new ArrayList<>();
	
	public Holiday () {
		
	}
	
	public Holiday(String title, LocalDate startDate, LocalDate endDate) {
		this.title = title;
		this.startDate = startDate;
		this.endDate = endDate;
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
	
	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate (LocalDate startDate) {
		this.startDate = startDate;
	}
	
	public LocalDate getEndDate() {
		return endDate;
	}

	public void setEndDate (LocalDate endDate) {
		this.endDate = endDate;
	}
	
	public List<Vacation> getVacations() {
		return vacations;
	}
	
	public void addVacation (Vacation vacation) {
		vacations.add(vacation);
	}
	
	public void removeVacation (Vacation vacation) {
		vacations.remove(vacation);
	}
}
