package by.epam.tc.hr_system.service;

import java.util.List;

import by.epam.tc.hr_system.domain.Education;
import by.epam.tc.hr_system.domain.PreviousPosition;
import by.epam.tc.hr_system.domain.Resume;
import by.epam.tc.hr_system.exception.ServiceException;

/**
 * 
 * Resume interface for resume.
 * 
 * @author Ivan Chernikau
 *
 */
public interface IResumeService {
	/**
	 * Validates all parameters for addiction resume and invokes appropriate DAO
	 * method.
	 * 
	 * @param resume
	 * @param userId
	 * @param educationFrom
	 * @param educationTo
	 * @param workFrom
	 * @param workTo
	 * @param filename
	 * @param doNotStudy
	 * @param doNotWork
	 * @throws ServiceException
	 */
	void addResume(Resume resume, int userId, String educationFrom, String educationTo, String workFrom, String workTo,
			String filename, String doNotStudy, String doNotWork) throws ServiceException;

	/**
	 * Validates all parameters for addiction education to resume and invokes appropriate DAO
	 * method.
	 * 
	 * @param education
	 * @param educationFrom
	 * @param educationTo
	 * @param idUser
	 * @throws ServiceException
	 */
	void addEducation(Education education, String educationFrom, String educationTo, int idUser)
			throws ServiceException;

	/**
	 * 
	 * Validates all parameters for addiction previous position and invokes appropriate DAO
	 * method.
	 * 
	 * @param prevPosition
	 * @param workFrom
	 * @param workTo
	 * @param idUser
	 * @throws ServiceException
	 */
	void addPreviousPosition(PreviousPosition prevPosition, String workFrom, String workTo, int idUser)
			throws ServiceException;

	/**
	 * Validates all parameters for removal resume and invokes appropriate DAO
	 * method.
	 * 
	 * @param idApplicant
	 * @throws ServiceException
	 */
	void deleteResume(int idApplicant) throws ServiceException;

	/**
	 * Gets count resume through invocation appropriate DAO
	 * method.
	 * 
	 * @return
	 * @throws ServiceException
	 */
	int getCountResumes() throws ServiceException;

	/**
	 * 
	 * Validates all parameters for getting applicant resume and invokes appropriate DAO
	 * method.
	 * 
	 * @param idResumeString
	 * @return
	 * @throws ServiceException
	 */
	Resume getApplicantResume(String idResumeString) throws ServiceException;

	
	/**
	 * Checks existing resume  through invocation appropriate DAO method.
	 * 
	 * @param idApplicant
	 * @throws ServiceException
	 */
	void checkExistingResume(int idApplicant) throws ServiceException;

	
	/**
	 * Validates all parameters for searching resume and invokes appropriate DAO
	 * method.
	 * 
	 * @param position
	 * @param kindEducation
	 * @return
	 * @throws ServiceException
	 */
	List<Resume> searchResumeByParameter(String position, String kindEducation) throws ServiceException;

}
