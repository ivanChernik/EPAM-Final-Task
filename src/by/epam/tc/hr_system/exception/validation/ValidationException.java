package by.epam.tc.hr_system.exception.validation;

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
