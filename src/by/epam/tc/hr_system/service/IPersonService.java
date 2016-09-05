package by.epam.tc.hr_system.service;

import by.epam.tc.hr_system.domain.Person;
import by.epam.tc.hr_system.exception.ServiceException;

public interface IPersonService {
	Person registerUser(String login, String password, String repeatedPassword, String role, String name,
			String surname, String patronymic, String email, String phoneNumber, String dateOfBirthday)
			throws ServiceException;

	Person authorizePerson(String login, String password) throws ServiceException;

	Person updateProfile(int idPerson, String name, String surname, String patronymic, String email,
			String phoneNumber, String dateOfBirthday) throws ServiceException;

}
