package ua.nure.moisieiev.summaryTask4.exception;

/**
 * An exception that provides information on an application error.
 * 
 * @author S.Moisieiev
 * 
 */
public class AppException extends Exception {

	private static final long serialVersionUID = 8288779062647218916L;

	public AppException() {
		super();
	}

	public AppException(String message, Throwable cause) {
		super(message, cause);
	}

	public AppException(String message) {
		super(message);
	}

}
