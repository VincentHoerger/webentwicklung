package server.exception;

@SuppressWarnings("serial")
public class MemberNotFoundException extends RuntimeException {

	public MemberNotFoundException(Long id) {
		super("Member not found with id " + id);
	}
	
	public MemberNotFoundException(String username) {
		super("Member not found with username " + username);
	}
}