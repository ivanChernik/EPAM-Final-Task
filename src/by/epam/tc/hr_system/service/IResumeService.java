package by.epam.tc.hr_system.service;


import java.util.List;

import by.epam.tc.hr_system.domain.Education;
import by.epam.tc.hr_system.domain.PreviousPosition;
import by.epam.tc.hr_system.domain.Resume;
import by.epam.tc.hr_system.exception.ServiceException;

public interface IResumeService {
	void addResume(Resume resume, int userId, String educationFrom, String educationTo, String workFrom, String workTo, String filename, String doNotStudy, String doNotWork) throws ServiceException;

	void addEducation(Education education, String educationFrom, String educationTo, int idUser) throws ServiceException;

	void addPreviousPosition(PreviousPosition prevPosition, String workFrom, String workTo, int idUser)
			throws ServiceException;
	
	void deleteResume(int idApplicant) throws ServiceException;
	
	int getCountResumes() throws ServiceException;

	Resume getApplicantResume(String idResumeString) throws ServiceException;
	
	void checkExistingResume(int idApplicant) throws ServiceException;
	
	List<Resume> searchResumesByParameter(String position, String kindEducation) throws ServiceException;

}
