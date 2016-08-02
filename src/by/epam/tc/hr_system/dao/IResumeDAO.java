package by.epam.tc.hr_system.dao;

import by.epam.tc.hr_system.domain.Resume;
import by.epam.tc.hr_system.exception.DAOException;

public interface IResumeDAO {
	
	void addResume(Resume resume, int idUser) throws DAOException;

}
