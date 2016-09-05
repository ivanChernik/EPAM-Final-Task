package by.epam.tc.hr_system.service.impl;

import java.sql.Date;
import java.util.Calendar;
import java.util.Formatter;
import java.util.List;

import org.apache.log4j.Logger;

import by.epam.tc.hr_system.dao.DAOFactory;
import by.epam.tc.hr_system.dao.IVacancyDAO;
import by.epam.tc.hr_system.dao.IVacancyResponceDAO;
import by.epam.tc.hr_system.domain.Vacancy;
import by.epam.tc.hr_system.domain.VacancyResponce;
import by.epam.tc.hr_system.exception.DAOException;
import by.epam.tc.hr_system.exception.ServiceException;
import by.epam.tc.hr_system.service.IVacancyService;
import by.epam.tc.hr_system.util.validation.StringConverter;

import static by.epam.tc.hr_system.util.validation.Validator.*;

public class VacancyServiceImpl implements IVacancyService {

	private static final Logger log = Logger.getLogger(VacancyServiceImpl.class);

	@Override
	public void addVacancy(String name, String description, String shortDescription, String requirement,
			String salaryString, String companyName, String contactInformation, String employment, int userID)
			throws ServiceException {

		name = validateRequiredString(name, 45);
		description = validateRequiredString(description, 800);
		shortDescription = validateRequiredString(shortDescription, 250);
		requirement = validateRequiredString(requirement, 800);
		int salary = StringConverter.parseStringToInt(salaryString);
		companyName = validateRequiredString(companyName, 50);
		contactInformation = validateRequiredString(contactInformation, 100);
		validateSelectedItem(Vacancy.getTimeList(), employment);
		validatePositiveInt(userID);

		Date dateSubmission = new Date(new java.util.Date().getTime());
		Vacancy vacancy = new Vacancy(name, description, shortDescription, requirement, salary, dateSubmission,
				Vacancy.OPEN_STATUS, companyName, contactInformation, employment);

		DAOFactory daoFactory = DAOFactory.getInstance();

		try {
			IVacancyDAO vacancyDAO = daoFactory.getVacancyDAO();
			vacancyDAO.addVacancy(vacancy, userID);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public void updateVacancy(String vacancyIDString, String name, String description, String shortDescription,
			String requirement, String salaryString, String companyName, String contactInformation, String employment,
			String status) throws ServiceException {

		int vacancyID = StringConverter.parseStringToInt(vacancyIDString);
		name = validateRequiredString(name, 45);
		description = validateRequiredString(description, 800);
		shortDescription = validateRequiredString(shortDescription, 250);
		requirement = validateRequiredString(requirement, 800);
		int salary = StringConverter.parseStringToInt(salaryString);
		companyName = validateRequiredString(companyName, 50);
		contactInformation = validateRequiredString(contactInformation, 100);
		validateSelectedItem(Vacancy.getTimeList(), employment);
		validateSelectedItem(Vacancy.getStatusList(), status);

		Vacancy vacancy = new Vacancy(vacancyID, name, description, shortDescription, requirement, salary, status,
				companyName, contactInformation, employment);
		DAOFactory daoFactory = DAOFactory.getInstance();

		try {
			IVacancyDAO vacancyDAO = daoFactory.getVacancyDAO();
			vacancyDAO.updateVacancy(vacancy);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}

	}

	@Override
	public List<Vacancy> getHRVacancies(int userID) throws ServiceException {
		validatePositiveInt(userID);

		DAOFactory daoFactory = DAOFactory.getInstance();

		List<Vacancy> listVacancy = null;
		try {
			IVacancyDAO vacancyDAO = daoFactory.getVacancyDAO();
			listVacancy = vacancyDAO.getHRVacancies(userID);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		return listVacancy;
	}

	@Override
	public List<Vacancy> getTopVacancies() throws ServiceException {
		DAOFactory daoFactory = DAOFactory.getInstance();

		List<Vacancy> listVacancy = null;
		try {
			IVacancyDAO vacancyDAO = daoFactory.getVacancyDAO();
			listVacancy = vacancyDAO.getTopVacancies();
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		return listVacancy;
	}

	@Override
	public int getCountVacancies() throws ServiceException {
		DAOFactory daoFactory = DAOFactory.getInstance();
		int countVacancies = 0;
		try {
			IVacancyDAO vacancyDAO = daoFactory.getVacancyDAO();
			countVacancies = vacancyDAO.getCountVacancies();
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		return countVacancies;
	}

	@Override
	public int getCountCompanies() throws ServiceException {
		DAOFactory daoFactory = DAOFactory.getInstance();
		int countCompanies = 0;
		try {
			IVacancyDAO vacancyDAO = daoFactory.getVacancyDAO();
			countCompanies = vacancyDAO.getCountCompanies();
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		return countCompanies;
	}

	@Override
	public Vacancy getVacancyByID(String vacancyIdString) throws ServiceException {

		int vacancyId = StringConverter.parseStringToInt(vacancyIdString);
		DAOFactory daoFactory = DAOFactory.getInstance();
		Vacancy vacancy = null;
		try {
			IVacancyDAO vacancyDAO = daoFactory.getVacancyDAO();
			vacancy = vacancyDAO.getVacancyByID(vacancyId);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		return vacancy;
	}

	@Override
	public List<Vacancy> deleteVacancies(String[] idVacancyArrayString, int idHR) throws ServiceException {

		int[] idResponceArray = StringConverter.parseArrayStringToInt(idVacancyArrayString);

		DAOFactory daoFactory = DAOFactory.getInstance();
		List<Vacancy> responceList = null;

		try {
			IVacancyDAO vacancyDAO = daoFactory.getVacancyDAO();
			vacancyDAO.deleteHRVacancies(idResponceArray);
			responceList = vacancyDAO.getHRVacancies(idHR);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}

		return responceList;
	}

	@Override
	public boolean checkVacancyToHR(int idHR, String idVacancyString) throws ServiceException {

		validatePositiveInt(idHR);
		int idVacancy = StringConverter.parseStringToInt(idVacancyString);

		DAOFactory daoFactory = DAOFactory.getInstance();
		boolean result = false;
		try {
			IVacancyDAO vacancyDAO = daoFactory.getVacancyDAO();
			result = vacancyDAO.checkVacancyToHR(idHR, idVacancy);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		return result;
	}

}
