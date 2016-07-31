package by.epam.tc.hr_system.service.impl;

import java.sql.Date;
import java.util.Calendar;

import org.apache.log4j.Logger;

import by.epam.tc.hr_system.dao.DAOFactory;
import by.epam.tc.hr_system.dao.IVacancyDAO;
import by.epam.tc.hr_system.domain.Vacancy;
import by.epam.tc.hr_system.exception.DAOException;
import by.epam.tc.hr_system.exception.ServiceException;
import by.epam.tc.hr_system.service.IVacancyService;

public class VacancyServiceImpl implements IVacancyService {

	private static final Logger log = Logger.getLogger(VacancyServiceImpl.class);

	@Override
	public void addVacancy(String name, String descrption, String shortDescription, String requirement,
			String salaryString, String companyName, String contactInformation, String employment, int userID)
			throws ServiceException {

		if (name == null || name.isEmpty()) {
			log.error("Error creation vacancy: name");
			throw new ServiceException("Error creation vacancy: name");
		}

		if (descrption == null || descrption.isEmpty()) {
			log.error("Error creation vacancy: descrption");
			throw new ServiceException("Error creation vacancy: descrption");
		}

		if (requirement == null || requirement.isEmpty()) {
			log.error("Error creation vacancy: requirement");
			throw new ServiceException("Error creation vacancy: requirement");
		}

		int salary = 0;

		if (salaryString == null || salaryString.isEmpty()) {
			log.error("Error creation vacancy: salary");
			throw new ServiceException("Error creation vacancy: salary");
		} else {
			try {
				salary = Integer.parseInt(salaryString);
			} catch (NumberFormatException e) {
				log.error("Error creation vacancy: salary");
				throw new ServiceException("Error creation vacancy: salary");
			}
		}

		if (companyName == null || companyName.isEmpty()) {
			log.error("Error creation vacancy: companyName");
			throw new ServiceException("Error creation vacancy: companyName");
		}

		if (contactInformation == null || contactInformation.isEmpty()) {
			log.error("Error creation vacancy: contactInformation");
			throw new ServiceException("Error creation vacancy: contactInformation");
		}

		if (employment == null || employment.isEmpty()) {
			log.error("Error creation vacancy: employment");
			throw new ServiceException("Error creation vacancy: employment");
		}

		Calendar calendar = Calendar.getInstance();

//		int year = calendar.get(calendar.YEAR);
//		int month = calendar.get(calendar.MONTH);
//		int day = calendar.get(calendar.DAY_OF_MONTH);
		Date dateSubmission = (Date) calendar.getTime();

		Vacancy vacancy = new Vacancy(name, descrption, shortDescription, requirement, salary, dateSubmission,
				Vacancy.OPEN_STATUS, companyName, contactInformation, employment);

		DAOFactory daoFactory = DAOFactory.getInstance();

		try {
			IVacancyDAO vacancyDAO = daoFactory.getVacancyDAO();
			vacancyDAO.addVacancy(vacancy, userID);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

}
