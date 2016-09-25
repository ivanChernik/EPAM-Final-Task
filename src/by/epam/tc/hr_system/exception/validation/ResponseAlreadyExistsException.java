package by.epam.tc.hr_system.exception.validation;

/**
 * Validation exception for exising response to vacancy
 * 
 * @author Ivan Chernikau
 *
 */
public class ResponseAlreadyExistsException extends ValidationException {

	public ResponseAlreadyExistsException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public ResponseAlreadyExistsException(Exception e) {
		super(e);
		// TODO Auto-generated constructor stub
	}

	public ResponseAlreadyExistsException(String message, Exception e) {
		super(message, e);
		// TODO Auto-generated constructor stub
	}

}
