package exceptions;

public class DatabaseErrorException extends Exception {
	private static final long serialVersionUID = 8110971096589094296L;

	public DatabaseErrorException(String message, Throwable err) {
		super(message, err);
	}
}
