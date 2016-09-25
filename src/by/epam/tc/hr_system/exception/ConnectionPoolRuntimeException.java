package by.epam.tc.hr_system.exception;

/**
 * Exception for connection pool initialization or dispose.
 * @author Ivan Chernikau
 *
 */
public class ConnectionPoolRuntimeException extends RuntimeException {
	
	public ConnectionPoolRuntimeException(String message) {
		super(message);
	}

	public ConnectionPoolRuntimeException(Exception e) {
		super(e);
	}

	public ConnectionPoolRuntimeException(String message, Exception e) {
		super(message, e);
	}

}
