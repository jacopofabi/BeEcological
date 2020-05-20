package error;

import java.sql.SQLException;

public class InexistentUsernameException extends SQLException {
	private static final long serialVersionUID = 1L;

	public InexistentUsernameException() {
		super();
	}
	
	public InexistentUsernameException(String message) {
		super(message);
	}
	
	public InexistentUsernameException(Throwable throwable) {
		super(throwable);
	}
}