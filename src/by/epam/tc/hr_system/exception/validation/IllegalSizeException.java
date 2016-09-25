package by.epam.tc.hr_system.exception.validation;

/**
 * Validation exception for illegal size.
 * 
 * @author Ivan Chernikau
 *
 */
public class IllegalSizeException extends ValidationException {

	public IllegalSizeException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public IllegalSizeException(Exception e) {
		super(e);
		// TODO Auto-generated constructor stub
	}

	public IllegalSizeException(String message, Exception e) {
		super(message, e);
		// TODO Auto-generated constructor stub
	}

}
