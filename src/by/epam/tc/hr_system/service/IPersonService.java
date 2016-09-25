package by.epam.tc.hr_system.service;

import by.epam.tc.hr_system.domain.Person;
import by.epam.tc.hr_system.exception.ServiceException;

/**
 * Service interface for person.
 * 
 * @author Ivan Chernikau
 *
 */
public interface IPersonService {
	/**
	 * Validates all parameters for registration and invokes appropriate DAO
	 * method.
	 * 
	 * @param login
	 * @param password
	 * @param repeatedPassword
	 * @param role
	 * @param name
	 * @param surname
	 * @param patronymic
	 * @param email
	 * @param phoneNumber
	 * @param dateOfBirthday
	 * @return
	 * @throws ServiceException
	 */
	Person registerUser(String login, String password, String repeatedPassword, String role, String name,
			String surname, String patronymic, String email, String phoneNumber, String dateOfBirthday)
			throws ServiceException;

	/**
	 * Validates all parameters for authorization and invokes appropriate DAO
	 * method
	 * 
	 * @param login
	 * @param password
	 * @return
	 * @throws ServiceException
	 */
	Person authorizePerson(String login, String password) throws ServiceException;

	/**
	 * Validates all parameters for updating resume and invokes appropriate DAO
	 * method
	 * 
	 * @param idPerson
	 * @param name
	 * @param surname
	 * @param patronymic
	 * @param email
	 * @param phoneNumber
	 * @param dateOfBirthday
	 * @return
	 * @throws ServiceException
	 */
	Person updateProfile(int idPerson, String name, String surname, String patronymic, String email, String phoneNumber,
			String dateOfBirthday) throws ServiceException;

}
