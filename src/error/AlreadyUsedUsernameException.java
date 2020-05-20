package error;

public class AlreadyUsedUsernameException extends Exception {
	private static final long serialVersionUID = 1L;

	public AlreadyUsedUsernameException() {
		super();
	}
	
	public AlreadyUsedUsernameException(String message) {
		super(message);
	}
	
	public AlreadyUsedUsernameException(Throwable throwable) {
		super(throwable);
	}
}