package by.epam.tc.hr_system.service;

import by.epam.tc.hr_system.domain.Resume;
import by.epam.tc.hr_system.exception.ServiceException;

public interface IResumeService {
	void addResume(Resume resume, int userId) throws ServiceException;
	
	int getCountResumes() throws ServiceException;
	
	Resume getApplicantResume(String idResumeString) throws ServiceException;

}
