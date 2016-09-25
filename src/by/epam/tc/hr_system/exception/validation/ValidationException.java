package by.epam.tc.hr_system.exception.validation;

/**
 * The super class for all exception, arise during validation.
 * 
 * @author Ivan Chernikau
 *
 */
public class ValidationException extends RuntimeException {
	
	public ValidationException(String message) {
		super(message);
	}

	public ValidationException(Exception e) {
		super(e);
	}

	public ValidationException(String message, Exception e) {
		super(message, e);
	}

}
