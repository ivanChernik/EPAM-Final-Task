package by.epam.tc.hr_system.exception.validation;

/**
 * Validation exception for empty value.
 * 
 * @author Ivan Chernikau
 *
 */
public class EmptyPropertyException extends ValidationException {

	public EmptyPropertyException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public EmptyPropertyException(Exception e) {
		super(e);
		// TODO Auto-generated constructor stub
	}

	public EmptyPropertyException(String message, Exception e) {
		super(message, e);
		// TODO Auto-generated constructor stub
	}

}
