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
import by.epam.tc.hr_system.exception.validation.ValidationExeception;
import by.epam.tc.hr_system.service.IUserService;
import by.epam.tc.hr_system.util.MessageManager;
import by.epam.tc.hr_system.util.validation.Validator;

public class UserServiceImpl implements IUserService {

	private static final Logger log = Logger.getLogger(UserServiceImpl.class);

	@Override
	public Person registerUser(String login, String password, String repeatedPassword, String role, String name,
			String surname, String patronymic, String email, String phoneNumber, String dateOfBirthday)
			throws ServiceException {

		Person person = null;

		DAOFactory daoFactory = DAOFactory.getInstance();
		IPersonDAO personDAO = null;

		try {
			Validator.validateString(login);

			try {
				personDAO = daoFactory.getPersonDAO();
				if (personDAO.searchSimilarLogin(login)) {
					log.error(MessageManager.ERROR_MESSAGE_LOGIN_ALREADY_EXISTS);
					throw new ServiceException(MessageManager.ERROR_MESSAGE_LOGIN_ALREADY_EXISTS);
				}
			} catch (DAOException e) {
				log.error("Error searching similar login");
				throw new ServiceException("Error searching similar login", e);
			}

			Validator.validateString(password);

			if (!password.equals(repeatedPassword)) {
				log.error(MessageManager.ERROR_MESSAGE_INVALID_REPETED_PASSWORD);
				throw new ServiceException(MessageManager.ERROR_MESSAGE_INVALID_REPETED_PASSWORD);
			}

			Validator.validateString(role);

			if (!role.equals(Person.APPLICANT_ROLE) || !role.equals(Person.HR_ROLE)) {
				log.error("Error registration: role");
				throw new ServiceException("Error registration: role");
			}

			Validator.validateString(name);

			Validator.validateString(surname);

			Validator.validateString(email);

			Validator.validateString(dateOfBirthday);

		} catch (ValidationExeception eValidation) {
			throw new ServiceException(eValidation);
		}

		Date birthdayDate = null;

		Formatter formatter = new Formatter();

		Calendar calendar = Calendar.getInstance();
		formatter.format("%tF", calendar);
		Date currentDate = Date.valueOf(formatter.toString());

		try {
			birthdayDate = Date.valueOf(dateOfBirthday);
		} catch (IllegalArgumentException e) {
			log.error("Error registration: dateOfBirthday");
			throw new ServiceException("Error registration: dateOfBirthday");
		}

		if (currentDate.after(birthdayDate)) {
			log.error("Error registration: dateOfBirthday");
			throw new ServiceException("Error registration: dateOfBirthday");
		}

		person = new Person(name, surname, patronymic, birthdayDate, email, phoneNumber, role);

		try {
			personDAO.registerPerson(login, password, person);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}

		return person;
	}

	@Override
	public Person authorizePerson(String login, String password) throws ServiceException {

		try {
			Validator.validateString(login);
			Validator.validateString(password);
		} catch (ValidationExeception eValidation) {
			throw new ServiceException(eValidation);
		}

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
