package by.epam.tc.hr_system.dao;

import by.epam.tc.hr_system.dao.impl.PersonDAOImpl;
import by.epam.tc.hr_system.dao.impl.ResumeDAOImpl;
import by.epam.tc.hr_system.dao.impl.VacancyDAOImpl;
import by.epam.tc.hr_system.exception.DAOException;

public class DAOFactory {
	private static final DAOFactory instance = new DAOFactory();
	private IPersonDAO personDAO = new PersonDAOImpl();
	private IResumeDAO resumeDAO = new ResumeDAOImpl();
	private IVacancyDAO vacancyDAO = new VacancyDAOImpl();

	public static DAOFactory getInstance() {
		return instance;
	}

	public IPersonDAO getPersonDAO() throws DAOException {
		return personDAO;
	}

	public IResumeDAO getResumeDAO() throws DAOException {
		return resumeDAO;
	}

	public IVacancyDAO getVacancyDAO() throws DAOException {
		return vacancyDAO;
	}

}
