package by.epam.tc.hr_system.dao;

import java.util.List;

import by.epam.tc.hr_system.domain.Education;
import by.epam.tc.hr_system.domain.PreviousPosition;
import by.epam.tc.hr_system.domain.Resume;
import by.epam.tc.hr_system.exception.DAOException;

/**
 * DAO interface for operation with resume.
 * 
 * @author Ivan Chernikau
 *
 */

public interface IResumeDAO {

	/**
	 * Add the resume. It's possible to have only one resume.
	 * 
	 * @param resume
	 * @param idUser
	 * @param doNotStudy
	 * @param doNotWork
	 * @throws DAOException
	 */

	void addResume(Resume resume, int idUser, String doNotStudy, String doNotWork) throws DAOException;

	/**
	 * Add education to existing resume.Amount is not limited.
	 * 
	 * @param education
	 * @param idUser
	 * @throws DAOException
	 */
	void addEducation(Education education, int idUser) throws DAOException;

	/**
	 * Add previos position to the existing resume.Amount is not limited.
	 * 
	 * @param prevPosition
	 * @param idUser
	 * @throws DAOException
	 */
	void addPreviousPosition(PreviousPosition prevPosition, int idUser) throws DAOException;

	/**
	 * Delete resume by user ID.
	 * 
	 * @param idUser
	 * @throws DAOException
	 */
	void deleteResume(int idUser) throws DAOException;

	/**
	 * Get count of resumes in database.
	 * 
	 * @return
	 * @throws DAOException
	 */
	int getCountResumes() throws DAOException;

	/**
	 * Get applicant resume by ID.
	 * 
	 * @param idResume
	 * @return
	 * @throws DAOException
	 */
	Resume getApplicantResume(int idResume) throws DAOException;

	/**
	 * Check existing resume of applicant.
	 * 
	 * @param idResume
	 * @return
	 * @throws DAOException
	 */
	boolean checkApplicantResume(int idResume) throws DAOException;

	/**
	 * Search resumes by parameters.
	 * 
	 * @param position
	 * @param kindEducation
	 * @return
	 * @throws DAOException
	 */
	List<Resume> searchResumeByParameters(String position, String kindEducation) throws DAOException;
}
