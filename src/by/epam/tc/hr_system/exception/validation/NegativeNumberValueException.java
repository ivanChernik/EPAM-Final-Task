package by.epam.tc.hr_system.exception.validation;

/**
 * Validation exception for negative number.
 * 
 * @author Ivan Chernikau
 *
 */
public class NegativeNumberValueException extends ValidationException {

	public NegativeNumberValueException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public NegativeNumberValueException(Exception e) {
		super(e);
		// TODO Auto-generated constructor stub
	}

	public NegativeNumberValueException(String message, Exception e) {
		super(message, e);
		// TODO Auto-generated constructor stub
	}

}
