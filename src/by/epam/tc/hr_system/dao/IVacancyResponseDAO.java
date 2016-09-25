package by.epam.tc.hr_system.dao;

import java.util.List;

import by.epam.tc.hr_system.domain.VacancyResponse;
import by.epam.tc.hr_system.exception.DAOException;

/**
 * DAO interface for response to vacancy.
 * 
 * @author Ivan Chernikau
 *
 */
public interface IVacancyResponseDAO {
	
	/**
	 * Add response to vacancy.
	 * 
	 * @param vacancyResponce
	 * @throws DAOException
	 */
	void addResponceToVacancy(VacancyResponse vacancyResponce) throws DAOException;
	
	/**
	 * Get list of responses for applicant.
	 * 
	 * @param idApplicant
	 * @return
	 * @throws DAOException
	 */
	List<VacancyResponse> getResponcesForApplicant(int idApplicant) throws DAOException;
	
	/**
	 * Get list of responses for vacancy.
	 * 
	 * @param idVacancy
	 * @return
	 * @throws DAOException
	 */
	List<VacancyResponse> getResponcesForVacancy(int idVacancy) throws DAOException;
	
	/**
	 * Change status of response.
	 * 
	 * @see VacancyResponse 
	 * 
	 * @param status
	 * @param idResponceArray
	 * @throws DAOException
	 */
	void changeStatus(String status, int[] idResponceArray) throws DAOException;
	
	/**
	 * Check existing response for vacancy.
	 * 
	 * @param idVacancy
	 * @param idApplicant
	 * @return true/false
	 * @throws DAOException
	 */
	boolean checkResponceToVacancy(int idVacancy, int idApplicant) throws DAOException;
}
