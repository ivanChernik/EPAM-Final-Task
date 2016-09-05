package by.epam.tc.hr_system.service;

import java.util.List;

import by.epam.tc.hr_system.domain.Vacancy;
import by.epam.tc.hr_system.exception.DAOException;
import by.epam.tc.hr_system.exception.ServiceException;

public interface IVacancyService {

	void addVacancy(String name, String descrption, String shortDescription, String requirement, String salaryString,
			String companyName, String contactInformation, String employment, int userID) throws ServiceException;

	void updateVacancy(String vacancyID, String name, String description, String shortDescription, String requirement,
			String salaryString, String companyName, String contactInformation, String employment, String status)
			throws ServiceException;

	List<Vacancy> getHRVacancies(int userID) throws ServiceException;

	List<Vacancy> getTopVacancies() throws ServiceException;

	List<Vacancy> deleteVacancies(String[] idVacancyArray, int idHR) throws ServiceException;

	Vacancy getVacancyByID(String vacancyIdString) throws ServiceException;

	int getCountVacancies() throws ServiceException;

	int getCountCompanies() throws ServiceException;

	boolean checkVacancyToHR(int idHR, String idVacancy) throws ServiceException;
}
