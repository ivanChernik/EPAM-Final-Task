package by.epam.tc.hr_system.dao;

import java.util.List;

import by.epam.tc.hr_system.domain.Vacancy;
import by.epam.tc.hr_system.exception.DAOException;
import by.epam.tc.hr_system.exception.ServiceException;

/**
 * DAO interface for operations with vacancies.
 * 
 * @author Ivan Chernikau
 *
 */
public interface IVacancyDAO {

	/**
	 * Add vacancy to HR-employee.
	 * 
	 * @param vacancy
	 * @param idHR
	 * @return
	 * @throws DAOException
	 */
	boolean addVacancy(Vacancy vacancy, int idHR) throws DAOException;

	/**
	 * Update a vacancy.
	 * 
	 * @param vacancy
	 * @throws DAOException
	 */
	void updateVacancy(Vacancy vacancy) throws DAOException;

	/**
	 * Remove vacancy by ID.
	 * 
	 * @param idVacancy
	 * @return
	 * @throws DAOException
	 */

	boolean removeVacancy(int idVacancy) throws DAOException;

	/**
	 * Get top vacancies.
	 * 
	 * @return
	 * @throws DAOException
	 */
	List<Vacancy> getTopVacancies() throws DAOException;

	/**
	 * Get vacancies of HR-employee.
	 * 
	 * @param idHR
	 * @return
	 * @throws DAOException
	 */
	List<Vacancy> getHRVacancies(int idHR) throws DAOException;

	/**
	 * Delete one or more vacancies of HR.
	 * 
	 * @param idVacancyArray
	 * @throws DAOException
	 */
	void deleteHRVacancies(int[] idVacancyArray) throws DAOException;

	/**
	 * Get vacancy by ID.
	 * 
	 * @param vacancyId
	 * @return vacancy
	 * @throws DAOException
	 */

	Vacancy getVacancyByID(int vacancyId) throws DAOException;

	/**
	 * Get amount vacancies in database.
	 * @return
	 * @throws DAOException
	 */
	int getCountVacancies() throws DAOException;

	/**
	 * get count companies in database.
	 * @return count companies
	 * @throws DAOException
	 */
	int getCountCompanies() throws DAOException;

	/**
	 * Check affiliation vacancy to HR-employee.
	 * 
	 * @param idHR
	 * @param idVacancy
	 * @return
	 * @throws DAOException
	 */
	boolean checkVacancyToHR(int idHR, int idVacancy) throws DAOException;

	/**
	 * Search vacancies by parameters.
	 * 
	 * @param position - prefered position
	 * @param employment
	 * @param salaryFrom
	 * @param salaryTo
	 * @return list of vacancies
	 * @throws DAOException
	 */
	List<Vacancy> searchVacancyByParameters(String position, String employment, int salaryFrom, int salaryTo)
			throws DAOException;

}
