package by.epam.tc.hr_system.exception.validation;

/**
 * Validation exception for invalid format image.
 * 
 * @author Ivan Chernikau
 *
 */
public class InvalidFormatImageException extends ValidationException {

	public InvalidFormatImageException(Exception e) {
		super(e);
		// TODO Auto-generated constructor stub
	}

	public InvalidFormatImageException(String message, Exception e) {
		super(message, e);
		// TODO Auto-generated constructor stub
	}

	public InvalidFormatImageException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

}
