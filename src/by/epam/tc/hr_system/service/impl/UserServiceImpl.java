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
		
		login = Validator.validateInputString(login);

		try {
			if (personDAO.searchSimilarLogin(login)) {
				log.warn("Warning the login already exists");
				throw new LoginAlreadyExistsExeption("Warning the login already exists");
			}
		} catch (DAOException e) {
			throw new ServiceException(e);
		}

		password = Validator.validateInputString(password);
		repeatedPassword = Validator.validateInputString(repeatedPassword);

		if (!password.equals(repeatedPassword)) {
			log.warn("Warning the password and repeated password already are not equal");
			throw new ServiceException("Warning the password and repeated password already are not equal");
		}

		Validator.validateInputString(role);
		Validator.validateOption(Person.getRoleList(), role);

		name = Validator.validateInputString(name);
		surname = Validator.validateInputString(surname);
		email = Validator.validateInputString(email);

		Date birthdayDate = Validator.parseStringToDate(dateOfBirthday);
		java.util.Date utilDate = new java.util.Date();
		Date currentDate = new Date(utilDate.getTime());
		Validator.validateDatesPeriod(currentDate, birthdayDate);
		
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

		Validator.validateInputString(login);
		Validator.validateInputString(password);

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
