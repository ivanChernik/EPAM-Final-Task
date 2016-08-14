package by.epam.tc.hr_system.dao;

import java.util.List;

import by.epam.tc.hr_system.domain.Vacancy;
import by.epam.tc.hr_system.exception.DAOException;
import by.epam.tc.hr_system.exception.ServiceException;

public interface IVacancyDAO {

	boolean addVacancy(Vacancy vacancy, int idHR) throws DAOException;

	boolean updateVacancy(Vacancy vacancy, int idHR) throws DAOException;

	boolean removeVacancy(int idVacancy) throws DAOException;
	
	List<Vacancy> getTopVacancies() throws DAOException;
	
	List<Vacancy> getHRVacancies(int idHR) throws DAOException;
	
	Vacancy getVacancyByID(int vacancyId) throws DAOException;
	
	
	int getCountVacancies() throws DAOException;
	
	int getCountCompanies() throws DAOException;

}
