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

	private static final Logger log = Logger.getLogger(UserServiceImpl.class);

	@Override
	public Person registerUser(String login, String password, String repeatedPassword, String role, String name,
			String surname, String patronymic, String email, String phoneNumber, String dateOfBirthday)
			throws ServiceException {

		if (!password.equals(repeatedPassword)) {
			log.error("Error registration: password");
			throw new ServiceException("Error registration: password");
		}

		Date birthdayDate = null;
		
		try {
			birthdayDate = Date.valueOf(dateOfBirthday);
		} catch (IllegalArgumentException e) {
			log.error("Error registration: dateOfBirthday");
			throw new ServiceException("Error registration: dateOfBirthday");
		}
		
		role = Person.APPLICANT_ROLE;

		Person person = new Person(name, surname, patronymic, birthdayDate, email, phoneNumber, role);

		DAOFactory daoFactory = DAOFactory.getInstance();

		try {
			IPersonDAO personDAO = daoFactory.getPersonDAO();
			personDAO.registerPerson(login, password, person);
		} catch (DAOException e) {

			throw new ServiceException("Error person dao registration", e);
		}

		return person;
	}

	// @Override
	// public void addPersonalInformation(String name, String surname, String
	// patronymic, String email,
	// String phoneNumber, String dateOfBirthday) throws ServiceException {
	//
	// if (personID < 0) {
	// log.error("Error registration: personID");
	// throw new ServiceException("Error registration: personID");
	// }
	//
	// if (name == null || name.isEmpty()) {
	// log.error("Error registration: name");
	// throw new ServiceException("Error registration: name");
	// }
	//
	// if (surname == null || surname.isEmpty()) {
	// log.error("Error registration: surname");
	// throw new ServiceException("Error registration: surname");
	// }
	//
	// if (patronymic == null || patronymic.isEmpty()) {
	// log.error("Error registration: patronymic");
	// throw new ServiceException("Error registration: patronymic");
	// }
	//
	// if (email == null || email.isEmpty()) {
	// log.error("Error registration: email");
	// throw new ServiceException("Error registration: email");
	// }
	//
	// if (phoneNumber == null || phoneNumber.isEmpty()) {
	// log.error("Error registration: phoneNumber");
	// throw new ServiceException("Error registration: phoneNumber");
	// }
	//
	// Date birthdayDate = null;
	//
	// if (dateOfBirthday == null || dateOfBirthday.isEmpty()) {
	// log.error("Error registration: dateOfBirthday");
	// throw new ServiceException("Error registration: dateOfBirthday");
	// } else {
	// try {
	// birthdayDate = Date.valueOf(dateOfBirthday);
	// } catch (IllegalArgumentException e) {
	// log.error("Error registration: dateOfBirthday");
	// throw new ServiceException("Error registration: dateOfBirthday");
	// }
	// }
	//
	// Person person = new Person(personID, name, surname, patronymic,
	// birthdayDate, email, phoneNumber, role);
	//
	// DAOFactory daoFactory = DAOFactory.getInstance();
	//
	// try {
	// IPersonDAO personDAO = daoFactory.getPersonDAO();
	// personDAO.addPersonInformation(person);
	// } catch (DAOException e) {
	// throw new ServiceException("Error person dao: addiction personal
	// information", e);
	// }

	// }

}
