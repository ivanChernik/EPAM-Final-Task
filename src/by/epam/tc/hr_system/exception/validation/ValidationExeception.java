package by.epam.tc.hr_system.exception.validation;

public class ValidationExeception extends RuntimeException {
	
	public ValidationExeception(String message) {
		super(message);
	}

	public ValidationExeception(Exception e) {
		super(e);
	}

	public ValidationExeception(String message, Exception e) {
		super(message, e);
	}

}
