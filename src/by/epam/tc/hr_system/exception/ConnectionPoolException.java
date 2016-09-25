package by.epam.tc.hr_system.exception;

/**
 * Exception for connection pool.
 * 
 * @author Ivan Chernikau
 *
 */
public class ConnectionPoolException extends Exception {

	public ConnectionPoolException(String message) {
		super(message);
	}

	public ConnectionPoolException(Exception e) {
		super(e);
	}

	public ConnectionPoolException(String message, Exception e) {
		super(message, e);
	}


}
