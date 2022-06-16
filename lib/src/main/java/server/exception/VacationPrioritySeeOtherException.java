package server.exception;

@SuppressWarnings("serial")
public class VacationPrioritySeeOtherException extends RuntimeException {
	public VacationPrioritySeeOtherException(long memberId, long vacationId) {
		super("VacationPriority for holidayId " + memberId + " and vacationId " + vacationId + " already present");
	}
}
