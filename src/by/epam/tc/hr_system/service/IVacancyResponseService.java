package by.epam.tc.hr_system.service;

import java.util.List;

import by.epam.tc.hr_system.domain.VacancyResponse;
import by.epam.tc.hr_system.exception.ServiceException;
import by.epam.tc.hr_system.exception.validation.ValidationException;

/**
 * Service interface for responce to vacancy.
 * 
 * @author Ivan Chernikau
 *
 */
public interface IVacancyResponseService {
	
	/**
	 * Validates all parameters for addiction response to vacancy and invokes appropriate DAO
	 * method.
	 * 
	 * @param idApplicant
	 * @param idVacancyString
	 * @throws ServiceException
	 */
	void addResponceToVacancy(int idApplicant, String idVacancyString) throws ServiceException;
	
	/**
	 * Get applicant responses  through invocation appropriate DAO method.
	 * 
	 * @param idApplicant
	 * @return
	 * @throws ServiceException
	 */
	List<VacancyResponse> getApplicantReponces(int idApplicant) throws ServiceException; 
	
	/**
	 * Get list of responses for vacancy through invocation appropriate DAO method.
	 * 
	 * @param idVacancyString
	 * @return
	 * @throws ServiceException
	 */
	List<VacancyResponse> getReponcesForVacancy(String idVacancyString) throws ServiceException;
	
	/**
	 * Validates all parameters for changing response status and invokes appropriate DAO
	 * method.
	 * 
	 * @param idResponceArrayString
	 * @param status
	 * @param idVacancy
	 * @return
	 * @throws ServiceException
	 */
	List<VacancyResponse> changeResponceStatus(String[] idResponceArrayString, String status, String idVacancy) throws ServiceException;

	/**
	 * Validates all parameters for checking affiliation response to vacancy and invokes appropriate DAO
	 * method.
	 * 
	 * @param idApplicant
	 * @param idVacancyString
	 * @throws ServiceException
	 */
	void checkResponceToVacancy(int idApplicant, String idVacancyString) throws ServiceException;
}
