package by.epam.tc.hr_system.dao;

import by.epam.tc.hr_system.dao.impl.PersonDAOImpl;
import by.epam.tc.hr_system.dao.impl.ResumeDAOImpl;
import by.epam.tc.hr_system.dao.impl.VacancyDAOImpl;
import by.epam.tc.hr_system.dao.impl.VacancyResponseDAOImpl;
import by.epam.tc.hr_system.exception.DAOException;

/**
 * Singleton for all DAOs
 * @author Ivan Chernikau
 *
 */

public class DAOFactory {
	private static final DAOFactory instance = new DAOFactory();
	private IPersonDAO personDAO = new PersonDAOImpl();
	private IResumeDAO resumeDAO = new ResumeDAOImpl();
	private IVacancyDAO vacancyDAO = new VacancyDAOImpl();
	private IVacancyResponseDAO vacancyResponceDAO = new VacancyResponseDAOImpl();

	public static DAOFactory getInstance() {
		return instance;
	}

	public IPersonDAO getPersonDAO() {
		return personDAO;
	}

	public IResumeDAO getResumeDAO() {
		return resumeDAO;
	}

	public IVacancyDAO getVacancyDAO() {
		return vacancyDAO;
	}

	public IVacancyResponseDAO getVacancyResponceDAO() {
		return vacancyResponceDAO;
	}

}
