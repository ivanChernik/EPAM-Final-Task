package by.epam.tc.hr_system.exception;

/**
 * Exception for DAO layer.
 * 
 * @author Ivan Chernikau
 *
 */
public class DAOException extends Exception {

	public DAOException(String message) {
		super(message);
	}

	public DAOException(Exception e) {
		super(e);
	}

	public DAOException(String message, Exception e) {
		super(message, e);
	}

}
