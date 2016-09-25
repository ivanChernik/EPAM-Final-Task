package by.epam.tc.hr_system.exception.validation;

/**
 * Validation exception for illegal values.
 * 
 * @author Ivan Chernikau
 *
 */
public class IllegalEntriedValueException extends ValidationException {

	public IllegalEntriedValueException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public IllegalEntriedValueException(Exception e) {
		super(e);
		// TODO Auto-generated constructor stub
	}

	public IllegalEntriedValueException(String message, Exception e) {
		super(message, e);
		// TODO Auto-generated constructor stub
	}

}
