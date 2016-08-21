package by.epam.tc.hr_system.service;

import java.util.List;

import by.epam.tc.hr_system.domain.VacancyResponce;
import by.epam.tc.hr_system.exception.ServiceException;
import by.epam.tc.hr_system.exception.validation.ValidationException;

public interface IVacancyResponceService {
	
	void addResponceToVacancy(VacancyResponce vacancyResponce) throws ServiceException;
	
	List<VacancyResponce> getApplicantReponces(int idApplicant) throws ServiceException; 
	
	List<VacancyResponce> getReponcesForVacancy(String idVacancyString) throws ServiceException;
	
	List<VacancyResponce> changeResponceStatus(String[] idResponceArrayString, String status, String idVacancy) throws ServiceException;

}
