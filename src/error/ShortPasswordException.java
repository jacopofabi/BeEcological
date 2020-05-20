package error;

public class ShortPasswordException extends Exception {
	private static final long serialVersionUID = 1L;

	public ShortPasswordException() {
		super();
	}
	
	public ShortPasswordException(String message) {
		super(message);
	}
	
	public ShortPasswordException(Throwable throwable) {
		super(throwable);
	}
}