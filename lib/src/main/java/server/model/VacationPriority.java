package server.model;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@Entity
@Table(name = "vacation_priorities")
@JsonPropertyOrder({ "priority", "vacation" })
public class VacationPriority {

	@EmbeddedId
	@JsonIgnore
	private VacationPriorityId id;
	
    @ManyToOne
    @MapsId("memberId")
    @JsonIgnore
    private Member member;

    @ManyToOne
    @MapsId("vacationId")
    private Vacation vacation;
    
    @Column
    private int priority;
    
    public VacationPriority() {
    }
    
    public VacationPriority(Member member, Vacation vacation, int priority) {
    	this.member = member;
    	this.vacation = vacation;
    	this.priority = priority;
    	this.id = new VacationPriorityId(member.getId(), vacation.getId());
    }
    
    public VacationPriorityId getId() {
    	return id;
    }

    public void setId(VacationPriorityId id) {
    	this.id = id;
    }
    
    public Member getMember() {
    	return member;
    }
    
    public void setMember(Member member) {
    	this.member = member;
    }
    
    public Vacation getVacation() {
    	return vacation;
    }
    
    public void setVacation(Vacation vacation) {
    	this.vacation = vacation;
    }
    
    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VacationPriority vacationPriority = (VacationPriority) o;
        return Objects.equals(member, vacationPriority.member) &&
                Objects.equals(vacation, vacationPriority.vacation);
    }

    @Override
    public int hashCode() {
        return Objects.hash(member, vacation);
    }
}
