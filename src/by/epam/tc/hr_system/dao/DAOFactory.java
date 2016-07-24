package by.epam.tc.hr_system.dao;

import by.epam.tc.hr_system.dao.impl.EducationDAOImpl;
import by.epam.tc.hr_system.dao.impl.PersonDAOImpl;
import by.epam.tc.hr_system.dao.impl.SkillDAOImpl;
import by.epam.tc.hr_system.dao.impl.VacancyDAOImpl;
import by.epam.tc.hr_system.exception.DAOException;

public class DAOFactory {
	private static final DAOFactory instance = new DAOFactory();
	private IPersonDAO personDAO = new PersonDAOImpl();
	private IEducationDAO educationDAO = new EducationDAOImpl();
	private ISkillDAO skillDAO = new SkillDAOImpl();
	private IVacancyDAO vacancyDAO = new VacancyDAOImpl();

	public static DAOFactory getInstance() {
		return instance;
	}

	public IPersonDAO getPersonDAO() throws DAOException {
		return personDAO;
	}

	public IEducationDAO getEducationDAO() throws DAOException {
		return educationDAO;
	}

	public ISkillDAO getSkillDAO() throws DAOException {
		return skillDAO;
	}

	public IVacancyDAO getVacancyDAO() throws DAOException {
		return vacancyDAO;
	}

}
