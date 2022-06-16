package server.exception;

@SuppressWarnings("serial")
public class VacationPriorityNotFoundException extends RuntimeException {
	public VacationPriorityNotFoundException(long memberId, long vacationId) {
		super("VacationPriority not found with memberId " + memberId + " and vacationId " + vacationId);
	}
}
