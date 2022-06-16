package server.exception;

@SuppressWarnings("serial")
public class MemberNotFoundException extends RuntimeException {

	public MemberNotFoundException(long id) {
		super("Member not found with id " + id);
	}
	
	public MemberNotFoundException(String username) {
		super("Member not found with username " + username);
	}
}