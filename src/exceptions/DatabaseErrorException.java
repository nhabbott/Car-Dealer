package exceptions;

public class DatabaseErrorException extends Exception {
	private static final long serialVersionUID = 8110971096589094296L;

	/**
	 * Class constructor
	 * @param message - Error message
	 * @param err - Error that caused the exception
	 */
	public DatabaseErrorException(String message, Throwable err) {
		super(message, err);
	}
}
