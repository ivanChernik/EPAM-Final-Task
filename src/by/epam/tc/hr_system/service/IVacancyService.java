package by.epam.tc.hr_system.service;

import java.util.List;

import by.epam.tc.hr_system.domain.Vacancy;
import by.epam.tc.hr_system.exception.DAOException;
import by.epam.tc.hr_system.exception.ServiceException;

/**
 * Service interface for vacancy.
 * 
 * @author Ivan Chernikau
 *
 */
public interface IVacancyService {
	/**
	 * Validates all parameters for addiction vacancy and invokes appropriate
	 * DAO method.
	 * 
	 * @param name
	 * @param descrption
	 * @param shortDescription
	 * @param requirement
	 * @param salaryString
	 * @param companyName
	 * @param contactInformation
	 * @param employment
	 * @param userID
	 * @throws ServiceException
	 */

	void addVacancy(String name, String descrption, String shortDescription, String requirement, String salaryString,
			String companyName, String contactInformation, String employment, int userID) throws ServiceException;

	/**
	 * Validates all parameters for updating vacancy and invokes appropriate DAO
	 * method.
	 * 
	 * @param vacancyID
	 * @param name
	 * @param description
	 * @param shortDescription
	 * @param requirement
	 * @param salaryString
	 * @param companyName
	 * @param contactInformation
	 * @param employment
	 * @param status
	 * @throws ServiceException
	 */
	void updateVacancy(String vacancyID, String name, String description, String shortDescription, String requirement,
			String salaryString, String companyName, String contactInformation, String employment, String status)
			throws ServiceException;

	/**
	 * Gets HR's vacancies through invocation appropriate DAO method.
	 * 
	 * @param userID
	 * @return
	 * @throws ServiceException
	 */
	List<Vacancy> getHRVacancies(int userID) throws ServiceException;

	/**
	 * Gets top of vacancies (9) through invocation appropriate DAO method.
	 * 
	 * @return
	 * @throws ServiceException
	 */
	List<Vacancy> getTopVacancies() throws ServiceException;

	/**
	 * Validates all parameters for removal one or more vacancies and invokes
	 * appropriate DAO method.
	 * 
	 * @param idVacancyArray
	 * @param idHR
	 * @return
	 * @throws ServiceException
	 */
	List<Vacancy> deleteVacancies(String[] idVacancyArray, int idHR) throws ServiceException;

	/**
	 * Validates all parameters for getting vacancy by ID and invokes
	 * appropriate DAO method.
	 * 
	 * @param vacancyIdString
	 * @return vacancy
	 * @throws ServiceException
	 */
	Vacancy getVacancyByID(String vacancyIdString) throws ServiceException;

	/**
	 * Gets count vacancies through invocation appropriate DAO method.
	 * 
	 * @return
	 * @throws ServiceException
	 */
	int getCountVacancies() throws ServiceException;

	/**
	 * Gets count companies through invocation appropriate DAO method.
	 * 
	 * @return
	 * @throws ServiceException
	 */
	int getCountCompanies() throws ServiceException;

	/**
	 * Checks affiliation vacancy to HR-employee through invocation appropriate DAO
	 * method.
	 * 
	 * @param idHR
	 * @param idVacancy
	 * @return
	 * @throws ServiceException
	 */
	boolean checkVacancyToHR(int idHR, String idVacancy) throws ServiceException;

	/**
	 *  Validates all parameters for searching vacancy by parameters and invokes
	 * appropriate DAO method.
	 * 
	 * @param titleVacancy
	 * @param employment
	 * @param salaryFromString
	 * @param salaryToString
	 * @return listVacancies
	 * @throws ServiceException
	 */
	List<Vacancy> searchVacancyByParameters(String titleVacancy, String employment, String salaryFromString,
			String salaryToString) throws ServiceException;
}
