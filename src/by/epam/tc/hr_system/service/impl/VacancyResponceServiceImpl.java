package by.epam.tc.hr_system.service.impl;

import java.sql.Date;
import java.util.Calendar;
import java.util.Formatter;
import java.util.List;

import org.apache.log4j.Logger;

import by.epam.tc.hr_system.command.impl.AuthorizationCommand;
import by.epam.tc.hr_system.dao.DAOFactory;
import by.epam.tc.hr_system.dao.IResumeDAO;
import by.epam.tc.hr_system.dao.IVacancyDAO;
import by.epam.tc.hr_system.dao.IVacancyResponseDAO;
import by.epam.tc.hr_system.domain.VacancyResponse;
import by.epam.tc.hr_system.exception.DAOException;
import by.epam.tc.hr_system.exception.ServiceException;
import by.epam.tc.hr_system.exception.validation.ResponseAlreadyExistsException;
import by.epam.tc.hr_system.exception.validation.ResumeDoesNotExistException;
import by.epam.tc.hr_system.exception.validation.ValidationException;
import by.epam.tc.hr_system.service.IVacancyResponseService;
import by.epam.tc.hr_system.util.validation.StringConverter;

import static by.epam.tc.hr_system.util.validation.Validator.*;

/**
 * Service implementation for response to vacancy.
 * 
 * @author Ivan Chernikau
 *
 */
public class VacancyResponceServiceImpl implements IVacancyResponseService {

	private static final Logger log = Logger.getLogger(VacancyResponceServiceImpl.class);

	@Override
	public void addResponceToVacancy(int idApplicant, String idVacancyString) throws ServiceException {

		validatePositiveInt(idApplicant);
		int idVacancy = StringConverter.parseStringToInt(idVacancyString);
		java.util.Date currentDate = new java.util.Date();
		
		VacancyResponse vacancyResponce = new VacancyResponse();
		
		vacancyResponce.getPerson().setId(idApplicant);
		vacancyResponce.getVacancy().setId(idVacancy);
		vacancyResponce.setDate(new Date(currentDate.getTime()));
		vacancyResponce.setStatus(VacancyResponse.NOT_VIEWED_STATUS);

		DAOFactory daoFactory = DAOFactory.getInstance();

		try {
			IResumeDAO resumeDAO = daoFactory.getResumeDAO();
			if (!resumeDAO.checkApplicantResume(vacancyResponce.getResume().getId())) {
				log.warn("Resume doesn't exist");
				throw new ResumeDoesNotExistException("Resume doesn't exist");
			}
			IVacancyResponseDAO vacancyResponceDAO = daoFactory.getVacancyResponceDAO();
			vacancyResponceDAO.addResponceToVacancy(vacancyResponce);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public List<VacancyResponse> getApplicantReponces(int idApplicant) throws ServiceException {

		DAOFactory daoFactory = DAOFactory.getInstance();

		validatePositiveInt(idApplicant);

		List<VacancyResponse> responceList = null;
		try {
			IVacancyResponseDAO vacancyResponceDAO = daoFactory.getVacancyResponceDAO();
			responceList = vacancyResponceDAO.getResponcesForApplicant(idApplicant);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}

		return responceList;
	}

	@Override
	public List<VacancyResponse> getReponcesForVacancy(String idVacancyString) throws ServiceException {

		int idVacancy = StringConverter.parseStringToInt(idVacancyString);

		DAOFactory daoFactory = DAOFactory.getInstance();
		List<VacancyResponse> responceList = null;

		try {
			IVacancyResponseDAO vacancyResponceDAO = daoFactory.getVacancyResponceDAO();
			responceList = vacancyResponceDAO.getResponcesForVacancy(idVacancy);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}

		return responceList;
	}

	@Override
	public List<VacancyResponse> changeResponceStatus(String[] idResponceArrayString, String status,
			String idVacancyString) throws ServiceException {

		int[] idResponceArray = StringConverter.parseArrayStringToInt(idResponceArrayString);
		validateSelectedItem(VacancyResponse.getStatusList(), status);
		int idVacancy = StringConverter.parseStringToInt(idVacancyString);

		DAOFactory daoFactory = DAOFactory.getInstance();
		List<VacancyResponse> responceList = null;

		try {
			IVacancyResponseDAO vacancyResponceDAO = daoFactory.getVacancyResponceDAO();
			vacancyResponceDAO.changeStatus(status, idResponceArray);
			responceList = vacancyResponceDAO.getResponcesForVacancy(idVacancy);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}

		return responceList;
	}

	@Override
	public void checkResponceToVacancy(int idApplicant, String idVacancyString) throws ServiceException {
		validatePositiveInt(idApplicant);
		int idVacancy = StringConverter.parseStringToInt(idVacancyString);

		DAOFactory daoFactory = DAOFactory.getInstance();

		try {
			IVacancyResponseDAO vacancyResponceDAO = daoFactory.getVacancyResponceDAO();
			// if responce exists
			if (vacancyResponceDAO.checkResponceToVacancy(idVacancy, idApplicant)) {
				log.warn("Responce has already applied");
				throw new ResponseAlreadyExistsException("Responce has already applied");
			}
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

}
