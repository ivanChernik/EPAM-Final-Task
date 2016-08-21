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
import by.epam.tc.hr_system.exception.validation.ValidationException;
import by.epam.tc.hr_system.service.IVacancyResponceService;
import by.epam.tc.hr_system.util.validation.Validator;

public class VacancyResponceServiceImpl implements IVacancyResponceService {

	@Override
	public void addResponceToVacancy(VacancyResponce vacancyResponce) throws ServiceException {

		Validator.validateInt(vacancyResponce.getResume().getId());
		Validator.validateInt(vacancyResponce.getVacancy().getId());
		vacancyResponce.setStatus(VacancyResponce.NOT_VIEWED_STATUS);

		java.util.Date currentDate = new java.util.Date();
		
		vacancyResponce.setDate(new Date(currentDate.getTime()));

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
		
		Validator.validateInt(idApplicant);		
		
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

		int idVacancy = Validator.parseStringToInt(idVacancyString);
		
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
			String idVacancyString) throws ServiceException {

		int[] idResponceArray = Validator.parseArrayStringToInt(idResponceArrayString);
		Validator.validateInputString(status);
		int idVacancy = Validator.parseStringToInt(idVacancyString);

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
