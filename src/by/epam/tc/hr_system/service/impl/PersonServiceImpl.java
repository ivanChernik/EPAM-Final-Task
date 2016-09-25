package by.epam.tc.hr_system.service.impl;

import java.sql.Date;
import java.util.Calendar;
import java.util.Formatter;

import org.apache.log4j.Logger;

import by.epam.tc.hr_system.dao.DAOFactory;
import by.epam.tc.hr_system.dao.IPersonDAO;
import by.epam.tc.hr_system.domain.Person;
import by.epam.tc.hr_system.exception.DAOException;
import by.epam.tc.hr_system.exception.ServiceException;
import by.epam.tc.hr_system.exception.validation.LoginAlreadyExistsExeption;
import by.epam.tc.hr_system.exception.validation.PasswordsNotEqualException;
import by.epam.tc.hr_system.exception.validation.ValidationException;
import by.epam.tc.hr_system.service.IPersonService;
import by.epam.tc.hr_system.util.ErrorMessage;
import by.epam.tc.hr_system.util.validation.StringConverter;

import static by.epam.tc.hr_system.util.validation.Validator.*;
/**
 * Service implementation for person.
 * 
 * @author Ivan Chernikau
 *
 */

public class PersonServiceImpl implements IPersonService {

	private static final Logger log = Logger.getLogger(PersonServiceImpl.class);

	@Override
	public Person registerUser(String login, String password, String repeatedPassword, String role, String name,
			String surname, String patronymic, String email, String phoneNumber, String dateOfBirthday)
			throws ServiceException {

		DAOFactory daoFactory = DAOFactory.getInstance();
		IPersonDAO personDAO = daoFactory.getPersonDAO();

		login = validateRequiredString(login, 30);

		try {
			if (personDAO.searchSimilarLogin(login)) {
				log.warn("Warning the login already exists");
				throw new LoginAlreadyExistsExeption("Warning the login already exists");
			}
		} catch (DAOException e) {
			throw new ServiceException(e);
		}

		validateSelectedItem(Person.getRoleList(), role);

		name = validateRequiredString(name, 50);
		surname = validateRequiredString(surname, 50);
		patronymic = validateNotRequiredString(patronymic, 50);
		phoneNumber = validateNotRequiredString(phoneNumber, 20);
		email = validateRequiredString(email, 30);

		Date birthdayDate = StringConverter.parseStringToDate(dateOfBirthday);
		Date currentDate = new Date(new java.util.Date().getTime());
		validateDatesPeriod(currentDate, birthdayDate);

		password = validateRequiredString(password, 45);
		repeatedPassword = validateRequiredString(repeatedPassword, 45);

		if (!password.equals(repeatedPassword)) {
			log.warn("Warning the password and repeated password already are not equal");
			throw new PasswordsNotEqualException("Warning the password and repeated password already are not equal");
		}

		Person person = new Person(name, surname, patronymic, birthdayDate, email, phoneNumber, role);

		try {
			personDAO.registerPerson(login, password, person);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}

		return person;
	}

	@Override
	public Person authorizePerson(String login, String password) throws ServiceException {

		validateRequiredString(login, 30);
		validateRequiredString(password, 45);

		DAOFactory daoFactory = DAOFactory.getInstance();
		Person person = null;

		try {
			IPersonDAO personDAO = daoFactory.getPersonDAO();
			person = personDAO.authorizePerson(login, password);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}

		return person;
	}

	@Override
	public Person updateProfile(int idPerson, String name, String surname, String patronymic, String email,
			String phoneNumber, String dateOfBirthday) throws ServiceException {

		validatePositiveInt(idPerson);
		name = validateRequiredString(name, 50);
		surname = validateRequiredString(surname, 50);
		patronymic = validateNotRequiredString(patronymic, 50);
		phoneNumber = validateNotRequiredString(phoneNumber, 20);
		email = validateRequiredString(email, 30);

		Date birthdayDate = StringConverter.parseStringToDate(dateOfBirthday);
		Date currentDate = new Date(new java.util.Date().getTime());
		validateDatesPeriod(currentDate, birthdayDate);

		Person person = new Person(idPerson, name, surname, patronymic, birthdayDate, email, phoneNumber);
		DAOFactory daoFactory = DAOFactory.getInstance();

		try {
			IPersonDAO personDAO = daoFactory.getPersonDAO();
			personDAO.updateProfile(person);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		return person;
	}
}
