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
import by.epam.tc.hr_system.service.IVacancyResponceService;

public class VacancyResponceServiceImpl implements IVacancyResponceService {

	@Override
	public void addResponceToVacancy(VacancyResponce vacancyResponce) throws ServiceException {

		if (vacancyResponce.getIdResume() < 0) {
			throw new ServiceException("Error addiction responce to Vacancy: idResume");
		}

		if (vacancyResponce.getIdVacancy() < 0) {
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
			responceList = vacancyResponceDAO.getApplicantResponce(idApplicant);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		
		return responceList;
	}

}