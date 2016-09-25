package by.epam.tc.hr_system.exception;

/**
 * Exception for service layer.
 * 
 * @author Ivan Chernikau
 *
 */
public class ServiceException  extends Exception {

	public ServiceException(String message) {
		super(message);
	}

	public ServiceException(Exception e) {
		super(e);
	}

	public ServiceException(String message, Exception e) {
		super(message, e);
	}
}
