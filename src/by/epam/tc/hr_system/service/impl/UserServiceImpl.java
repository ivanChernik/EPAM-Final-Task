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
import by.epam.tc.hr_system.service.IUserService;
import by.epam.tc.hr_system.util.MessageManager;
import by.epam.tc.hr_system.util.validation.Validator;

public class UserServiceImpl implements IUserService {

	private static final Logger log = Logger.getLogger(UserServiceImpl.class);

	@Override
	public Person registerUser(String login, String password, String repeatedPassword, String role, String name,
			String surname, String patronymic, String email, String phoneNumber, String dateOfBirthday)
			throws ServiceException {
		
		DAOFactory daoFactory = DAOFactory.getInstance();
		IPersonDAO personDAO = daoFactory.getPersonDAO();
		
		login = Validator.validateInputRequiredString(login);

		try {
			if (personDAO.searchSimilarLogin(login)) {
				log.warn("Warning the login already exists");
				throw new LoginAlreadyExistsExeption("Warning the login already exists");
			}
		} catch (DAOException e) {
			throw new ServiceException(e);
		}

		Validator.validateInputRequiredString(role);
		Validator.validateSelectedItem(Person.getRoleList(), role);

		name = Validator.validateInputRequiredString(name);
		surname = Validator.validateInputRequiredString(surname);
		email = Validator.validateInputRequiredString(email);
		patronymic = Validator.validateNotRequiredString(patronymic);
		phoneNumber = Validator.validateNotRequiredString(phoneNumber);

		Date birthdayDate = Validator.parseStringToDate(dateOfBirthday);
		Date currentDate = new Date(new java.util.Date().getTime());
		Validator.validateDatesPeriod(currentDate, birthdayDate);
		
		password = Validator.validateInputRequiredString(password);
		repeatedPassword = Validator.validateInputRequiredString(repeatedPassword);

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

		Validator.validateInputRequiredString(login);
		Validator.validateInputRequiredString(password);

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

}
