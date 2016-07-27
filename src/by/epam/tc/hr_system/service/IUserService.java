package by.epam.tc.hr_system.service;

import by.epam.tc.hr_system.domain.Person;
import by.epam.tc.hr_system.exception.ServiceException;

public interface IUserService {
	
	String MESSAGE_INVALID_PASSWORD = "Invalid password";
	String MESSAGE_LOGIN_ALREADY_EXISTS = "The login already exists";

	Person registerUser(String login, String password, String repeatedPassword, String role, String name,
			String surname, String patronymic, String email, String phoneNumber, String dateOfBirthday) throws ServiceException;
	
	Person authorizePerson(String login, String password) throws ServiceException;

}
