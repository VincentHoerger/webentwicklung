package server.exception;

@SuppressWarnings("serial")
public class VacationNotFoundException extends RuntimeException {
	public VacationNotFoundException(long id) {
		super("Vacation not found with id " + id);
	}
	
	public VacationNotFoundException(String title) {
		super("Vacation not found with title " + title);
	}
}
