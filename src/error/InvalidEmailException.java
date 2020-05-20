package error;

public class InvalidEmailException extends Exception {
	private static final long serialVersionUID = 1L;

	public InvalidEmailException() {
		super();
	}
	
	public InvalidEmailException(String message) {
		super(message);
	}
	
	public InvalidEmailException(Throwable throwable) {
		super(throwable);
	}
}