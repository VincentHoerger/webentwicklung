package server.model;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@SuppressWarnings("serial")
@Embeddable
public class VacationPriorityId implements Serializable {

	@Column(name = "member_id")
	long memberId;

	@Column(name = "vacation_id")
	long vacationId;

	public VacationPriorityId() {
	}

	public VacationPriorityId(long memberId, long vacationId) {
		this.memberId = memberId;
		this.vacationId = vacationId;
	}

	public long getMemberId() {
		return memberId;
	}

	public void setMemberId(long memberId) {
		this.memberId = memberId;
	}

	public long getVacationId() {
		return vacationId;
	}

	public void setVacationId(long vacationId) {
		this.vacationId = vacationId;
	}
	
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
 
        if (o == null || getClass() != o.getClass())
            return false;
 
        VacationPriorityId that = (VacationPriorityId) o;
        return Objects.equals(memberId, that.memberId) &&
               Objects.equals(vacationId, that.vacationId);
    }
 
    @Override
    public int hashCode() {
        return Objects.hash(memberId, vacationId);
    }
}
