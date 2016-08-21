package by.epam.tc.hr_system.exception.validation;

public class LoginAlreadyExistsExeption extends ValidationException {

	public LoginAlreadyExistsExeption(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public LoginAlreadyExistsExeption(Exception e) {
		super(e);
		// TODO Auto-generated constructor stub
	}

	public LoginAlreadyExistsExeption(String message, Exception e) {
		super(message, e);
		// TODO Auto-generated constructor stub
	}

}
