package by.epam.tc.hr_system.service;

import java.util.List;

import by.epam.tc.hr_system.domain.VacancyResponce;
import by.epam.tc.hr_system.exception.ServiceException;

public interface IVacancyResponceService {
	
	void addResponceToVacancy(VacancyResponce vacancyResponce) throws ServiceException;
	
	List<VacancyResponce> getApplicantReponces(int idApplicant) throws ServiceException; 

}
