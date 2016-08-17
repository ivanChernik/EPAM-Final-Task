package by.epam.tc.hr_system.service;

import javax.servlet.http.Part;

import by.epam.tc.hr_system.domain.Resume;
import by.epam.tc.hr_system.exception.ServiceException;

public interface IResumeService {
	void addResume(Resume resume, int userId,String educationFrom,String educationTo,String workFrom, String workTo,Part filePart,String filename,String mimeType,String realpath) throws ServiceException;
	
	int getCountResumes() throws ServiceException;
	
	Resume getApplicantResume(String idResumeString) throws ServiceException;

}
