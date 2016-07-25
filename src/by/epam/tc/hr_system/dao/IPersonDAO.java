package by.epam.tc.hr_system.dao;

import java.util.List;

import by.epam.tc.hr_system.domain.Person;
import by.epam.tc.hr_system.exception.DAOException;

public interface IPersonDAO {

	boolean searchSimilarLogin(String login) throws DAOException;

	void registerPerson(String login, String password, Person person) throws DAOException;

	boolean removePersonByID(int idUser) throws DAOException;

	boolean updatePersonInformation(Person person) throws DAOException;

	Person searchPersonByEmail(String email) throws DAOException;

	List<Person> searchPersonByNames(String name, String surname, String middleName) throws DAOException;

}
