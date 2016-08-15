package by.epam.tc.hr_system.exception;

public class ValidationExeception extends Exception {
	
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
