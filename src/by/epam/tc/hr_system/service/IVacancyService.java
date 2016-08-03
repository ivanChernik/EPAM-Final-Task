package by.epam.tc.hr_system.service;

import java.util.List;

import by.epam.tc.hr_system.domain.Vacancy;
import by.epam.tc.hr_system.exception.DAOException;
import by.epam.tc.hr_system.exception.ServiceException;

public interface IVacancyService {

	void addVacancy(String name, String descrption, String shortDescription, String requirement, String salaryString,
			String companyName, String contactInformation, String employment, int userID) throws ServiceException;

	List<Vacancy> getHRVacancies(int userID) throws ServiceException;

	List<Vacancy> getTopVacancies() throws ServiceException;
	
	Vacancy getVacancyByID(int vacancyId) throws ServiceException;

	int getCountVacancies() throws ServiceException;
	
	int getCountResumes() throws ServiceException;
	
	int getCountCompanies() throws ServiceException;
}
