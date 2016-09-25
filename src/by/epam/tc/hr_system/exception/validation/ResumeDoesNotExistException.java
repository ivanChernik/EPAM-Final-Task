package by.epam.tc.hr_system.exception.validation;

/**
 * Validation exception for not existing resume.
 * 
 * @author Ivan Chernikau
 *
 */
public class ResumeDoesNotExistException extends ValidationException {

	public ResumeDoesNotExistException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public ResumeDoesNotExistException(Exception e) {
		super(e);
		// TODO Auto-generated constructor stub
	}

	public ResumeDoesNotExistException(String message, Exception e) {
		super(message, e);
		// TODO Auto-generated constructor stub
	}

}
