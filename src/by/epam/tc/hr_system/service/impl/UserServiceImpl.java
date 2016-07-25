package by.epam.tc.hr_system.service.impl;

import java.sql.Date;

import org.apache.log4j.Logger;

import by.epam.tc.hr_system.dao.DAOFactory;
import by.epam.tc.hr_system.dao.IPersonDAO;
import by.epam.tc.hr_system.domain.Person;
import by.epam.tc.hr_system.exception.DAOException;
import by.epam.tc.hr_system.exception.ServiceException;
import by.epam.tc.hr_system.service.IUserService;

public class UserServiceImpl implements IUserService {

	private static final String HR_RU = "Работадатель";
	private static final String APPLICANT_RU = "Соискатель";
	private static final Logger log = Logger.getLogger(UserServiceImpl.class);

	@Override
	public Person registerUser(String login, String password, String repeatedPassword, String role, String name,
			String surname, String patronymic, String email, String phoneNumber, String dateOfBirthday)
			throws ServiceException {

		if (login == null || login.isEmpty()) {
			log.error("Error registration: login");
			throw new ServiceException("Error registration: login");
		}

		DAOFactory daoFactory = DAOFactory.getInstance();
		IPersonDAO personDAO = null;
		try {
			personDAO = daoFactory.getPersonDAO();
			if (personDAO.searchSimilarLogin(login)) {
				log.error(MESSAGE_LOGIN_ALREADY_EXISTS);
				throw new ServiceException(MESSAGE_LOGIN_ALREADY_EXISTS);
			}
		} catch (DAOException e) {
			log.error("Error searching similar login");
			throw new ServiceException("Error searching similar login",e);
		}

		if (password == null || password.isEmpty() || repeatedPassword == null || repeatedPassword.isEmpty()) {
			log.error("Error registration: password");
			throw new ServiceException("Error registration: password");
		}

		if (!password.equals(repeatedPassword)) {
			log.error(MESSAGE_INVALID_PASSWORD);
			throw new ServiceException(MESSAGE_INVALID_PASSWORD);
		}

		if (role == null || role.isEmpty()) {
			log.error("Error registration: password");
			throw new ServiceException("Error registration: password");
		}

		if (role.equals(APPLICANT_RU)) {
			role = Person.APPLICANT_ROLE;
		} else if (role.equals(HR_RU)) {
			role = Person.HR_ROLE;
		} else {
			throw new ServiceException("Error registration: role");
		}

		if (name == null || name.isEmpty()) {
			log.error("Error registration: name");
			throw new ServiceException("Error registration: name");
		}

		if (surname == null || surname.isEmpty()) {
			log.error("Error registration: surname");
			throw new ServiceException("Error registration: surname");
		}

		if (patronymic == null || patronymic.isEmpty()) {
			log.error("Error registration: patronymic");
			throw new ServiceException("Error registration: patronymic");
		}

		if (email == null || email.isEmpty()) {
			log.error("Error registration: email");
			throw new ServiceException("Error registration: email");
		}

		if (phoneNumber == null || phoneNumber.isEmpty()) {
			log.error("Error registration: phoneNumber");
			throw new ServiceException("Error registration: phoneNumber");
		}

		Date birthdayDate = null;

		try {
			birthdayDate = Date.valueOf(dateOfBirthday);
		} catch (IllegalArgumentException e) {
			log.error("Error registration: dateOfBirthday");
			throw new ServiceException("Error registration: dateOfBirthday");
		}

		Person person = new Person(name, surname, patronymic, birthdayDate, email, phoneNumber, role);

		try {
			personDAO.registerPerson(login, password, person);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}

		return person;
	}

}
