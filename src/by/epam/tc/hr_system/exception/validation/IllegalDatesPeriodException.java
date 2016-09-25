package by.epam.tc.hr_system.exception.validation;

/**
 * Validation exception for illegal period of between dates.
 * 
 * @author Ivan Chernikau
 *
 */
public class IllegalDatesPeriodException extends ValidationException {

	public IllegalDatesPeriodException(Exception e) {
		super(e);
		// TODO Auto-generated constructor stub
	}

	public IllegalDatesPeriodException(String message, Exception e) {
		super(message, e);
		// TODO Auto-generated constructor stub
	}

	public IllegalDatesPeriodException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

}
