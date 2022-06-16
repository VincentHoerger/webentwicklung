package server.exception;

@SuppressWarnings("serial")
public class HolidayNotFoundException extends RuntimeException{
	public HolidayNotFoundException(long id) {
		super("Holiday not found with id " + id);
	}
	
	public HolidayNotFoundException(String title) {
		super("Holiday not found with title " + title);
	}
}
