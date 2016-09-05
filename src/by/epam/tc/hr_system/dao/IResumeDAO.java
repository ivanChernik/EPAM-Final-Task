package by.epam.tc.hr_system.dao;

import by.epam.tc.hr_system.domain.Education;
import by.epam.tc.hr_system.domain.PreviousPosition;
import by.epam.tc.hr_system.domain.Resume;
import by.epam.tc.hr_system.exception.DAOException;

public interface IResumeDAO {

	void addResume(Resume resume, int idUser) throws DAOException;

	void addEducation(Education education, int idUser) throws DAOException;
	
	void addPreviousPosition(PreviousPosition prevPosition, int idUser) throws DAOException;

	int getCountResumes() throws DAOException;

	Resume getApplicantResume(int idResume) throws DAOException;

	boolean checkApplicantResume(int idResume) throws DAOException;
}
