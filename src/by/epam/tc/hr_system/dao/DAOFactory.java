package by.epam.tc.hr_system.dao;

import by.epam.tc.hr_system.dao.impl.PersonDAOImpl;
import by.epam.tc.hr_system.dao.impl.ResumeDAOImpl;
import by.epam.tc.hr_system.dao.impl.VacancyDAOImpl;
import by.epam.tc.hr_system.dao.impl.VacancyResponceDAOImpl;
import by.epam.tc.hr_system.exception.DAOException;

public class DAOFactory {
	private static final DAOFactory instance = new DAOFactory();
	private IPersonDAO personDAO = new PersonDAOImpl();
	private IResumeDAO resumeDAO = new ResumeDAOImpl();
	private IVacancyDAO vacancyDAO = new VacancyDAOImpl();
	private IVacancyResponceDAO vacancyResponceDAO = new VacancyResponceDAOImpl();

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

	public IVacancyResponceDAO getVacancyResponceDAO() {
		return vacancyResponceDAO;
	}

}
