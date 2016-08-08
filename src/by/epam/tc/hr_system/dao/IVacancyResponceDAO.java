package by.epam.tc.hr_system.dao;

import java.util.List;

import by.epam.tc.hr_system.domain.VacancyResponce;
import by.epam.tc.hr_system.exception.DAOException;

public interface IVacancyResponceDAO {
	
	void addResponceToVacancy(VacancyResponce vacancyResponce) throws DAOException;
	
	List<VacancyResponce> getApplicantResponce(int idApplicant) throws DAOException;

}
