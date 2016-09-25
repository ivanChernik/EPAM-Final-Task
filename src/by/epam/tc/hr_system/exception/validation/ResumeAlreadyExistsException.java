package by.epam.tc.hr_system.exception.validation;

/**
 * Validation exception for existing resume.
 * 
 * @author Ivan Chernikau
 *
 */
public class ResumeAlreadyExistsException extends ValidationException {

	public ResumeAlreadyExistsException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public ResumeAlreadyExistsException(Exception e) {
		super(e);
		// TODO Auto-generated constructor stub
	}

	public ResumeAlreadyExistsException(String message, Exception e) {
		super(message, e);
		// TODO Auto-generated constructor stub
	}

}
