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
import by.epam.tc.hr_system.util.validation.Validator;

public class VacancyServiceImpl implements IVacancyService {

	private static final Logger log = Logger.getLogger(VacancyServiceImpl.class);

	@Override
	public void addVacancy(String name, String descrption, String shortDescription, String requirement,
			String salaryString, String companyName, String contactInformation, String employment, int userID)
			throws ServiceException {

		Validator.validateInputRequiredString(name);
		Validator.validateTextAreaRequiredString(descrption);
		Validator.validateTextAreaRequiredString(requirement);
		int salary = Validator.parseStringToInt(salaryString);
		Validator.validateInputRequiredString(companyName);
		Validator.validateInputRequiredString(contactInformation);
		Validator.validateSelectedItem(Vacancy.getTimeList(), employment);
		Validator.validateInt(userID);

		Date dateSubmission = new Date(new java.util.Date().getTime());
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

	@Override
	public List<Vacancy> getHRVacancies(int userID) throws ServiceException {
		Validator.validateInt(userID);

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

		int vacancyId = Validator.parseStringToInt(vacancyIdString);
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
		
		int[] idResponceArray = Validator.parseArrayStringToInt(idVacancyArrayString);

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
		
		Validator.validateInt(idHR);
		int idVacancy = Validator.parseStringToInt(idVacancyString);
		
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
