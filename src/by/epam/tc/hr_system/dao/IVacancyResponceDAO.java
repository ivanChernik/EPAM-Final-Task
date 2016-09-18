package by.epam.tc.hr_system.dao;

import java.util.List;

import by.epam.tc.hr_system.domain.VacancyResponce;
import by.epam.tc.hr_system.exception.DAOException;

public interface IVacancyResponceDAO {
	
	void addResponceToVacancy(VacancyResponce vacancyResponce) throws DAOException;
	
	List<VacancyResponce> getResponcesForApplicant(int idApplicant) throws DAOException;
	
	List<VacancyResponce> getResponcesForVacancy(int idVacancy) throws DAOException;
	
	void changeStatus(String status, int[] idResponceArray) throws DAOException;
	
	boolean checkResponceToVacancy(int idVacancy, int idApplicant) throws DAOException;
}
