package server.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "holidays")
public class Holiday {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Size(min = 0, max = 50)
	@Column(unique = true) private String title;
	
	@DateTimeFormat
	@Column private Date startDate;
	
	@DateTimeFormat
	@Column private Date endDate;
	
	public Holiday () {
		
	}
	
	public Holiday(String title, Date startDate, Date endDate) {
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
	
	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate (Date startDate) {
		this.startDate = startDate;
	}
	
	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate (Date endDate) {
		this.endDate = endDate;
	}
}
