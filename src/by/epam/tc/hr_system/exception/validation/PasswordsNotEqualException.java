package by.epam.tc.hr_system.exception.validation;

/**
 * Validation exception for different password and repeated password.
 * 
 * @author Ivan Chernikau
 *
 */
public class PasswordsNotEqualException extends ValidationException {

	public PasswordsNotEqualException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public PasswordsNotEqualException(Exception e) {
		super(e);
		// TODO Auto-generated constructor stub
	}

	public PasswordsNotEqualException(String message, Exception e) {
		super(message, e);
		// TODO Auto-generated constructor stub
	}

}
