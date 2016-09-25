package by.epam.tc.hr_system.exception.validation;

/**
 * Validation exception for not picked photo.
 * 
 * @author Ivan Chernikau
 *
 */
public class PhotoNotChosenException extends ValidationException {

	public PhotoNotChosenException(Exception e) {
		super(e);
		// TODO Auto-generated constructor stub
	}

	public PhotoNotChosenException(String message, Exception e) {
		super(message, e);
		// TODO Auto-generated constructor stub
	}

	public PhotoNotChosenException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

}
