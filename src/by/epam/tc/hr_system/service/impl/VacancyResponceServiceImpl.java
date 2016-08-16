package by.epam.tc.hr_system.service.impl;

import java.sql.Date;
import java.util.Calendar;
import java.util.Formatter;
import java.util.List;

import by.epam.tc.hr_system.dao.DAOFactory;
import by.epam.tc.hr_system.dao.IVacancyResponceDAO;
import by.epam.tc.hr_system.domain.VacancyResponce;
import by.epam.tc.hr_system.exception.DAOException;
import by.epam.tc.hr_system.exception.ServiceException;
import by.epam.tc.hr_system.exception.ValidationExeception;
import by.epam.tc.hr_system.service.IVacancyResponceService;
import by.epam.tc.hr_system.util.validation.Validator;

public class VacancyResponceServiceImpl implements IVacancyResponceService {

	private static final String ID_VACANCY = "idVacancy";
	private static final String STATUS = "status";
	private static final String ID_VACANCY_STRING = "idVacancyString";

	@Override
	public void addResponceToVacancy(VacancyResponce vacancyResponce) throws ServiceException {

		if (vacancyResponce.getResume().getId() < 0) {
			throw new ServiceException("Error addiction responce to Vacancy: idResume");
		}

		if (vacancyResponce.getVacancy().getId() < 0) {
			throw new ServiceException("Error addiction responce to Vacancy: idVacancy");
		}

		vacancyResponce.setStatus(VacancyResponce.NOT_VIEWED_STATUS);

		Formatter formatter = new Formatter();
		Calendar calendar = Calendar.getInstance();
		formatter.format("%tF", calendar);
		vacancyResponce.setDate(Date.valueOf(formatter.toString()));

		DAOFactory daoFactory = DAOFactory.getInstance();

		try {
			IVacancyResponceDAO vacancyResponceDAO = daoFactory.getVacancyResponceDAO();
			vacancyResponceDAO.addResponceToVacancy(vacancyResponce);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public List<VacancyResponce> getApplicantReponces(int idApplicant) throws ServiceException {

		DAOFactory daoFactory = DAOFactory.getInstance();

		List<VacancyResponce> responceList = null;

		try {
			IVacancyResponceDAO vacancyResponceDAO = daoFactory.getVacancyResponceDAO();
			responceList = vacancyResponceDAO.getResponcesForApplicant(idApplicant);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}

		return responceList;
	}

	@Override
	public List<VacancyResponce> getReponcesForVacancy(String idVacancyString) throws ServiceException {

		int idVacancy = 0;

		try {
			idVacancy = Validator.parseStringToInt(idVacancyString, ID_VACANCY_STRING);
		} catch (ValidationExeception eValidation) {
			throw new ServiceException(eValidation);
		}

		DAOFactory daoFactory = DAOFactory.getInstance();
		List<VacancyResponce> responceList = null;

		try {
			IVacancyResponceDAO vacancyResponceDAO = daoFactory.getVacancyResponceDAO();
			responceList = vacancyResponceDAO.getResponcesForVacancy(idVacancy);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}

		return responceList;
	}

	@Override
	public List<VacancyResponce> changeResponceStatus(String[] idResponceArrayString, String status,
			String idVacancyString) throws ServiceException, ValidationExeception {

		int[] idResponceArray;
		int idVacancy = 0;

		idResponceArray = Validator.parseArrayStringToInt(idResponceArrayString);
		Validator.validateString(status, STATUS);
		idVacancy = Validator.parseStringToInt(idVacancyString, ID_VACANCY);

		DAOFactory daoFactory = DAOFactory.getInstance();
		List<VacancyResponce> responceList = null;

		try {
			IVacancyResponceDAO vacancyResponceDAO = daoFactory.getVacancyResponceDAO();
			vacancyResponceDAO.changeStatus(status, idResponceArray);
			responceList = vacancyResponceDAO.getResponcesForVacancy(idVacancy);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}

		return responceList;
	}

}
