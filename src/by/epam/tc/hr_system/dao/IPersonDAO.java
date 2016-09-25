package by.epam.tc.hr_system.dao;


import by.epam.tc.hr_system.domain.Person;
import by.epam.tc.hr_system.exception.DAOException;

/**
 * DAO interface for person.
 * 
 * @author Ivan Chernikau
 *
 */
public interface IPersonDAO {

	/**
	 * Search similar login entred by User.
	 * 
	 * @param login
	 * @return true/false
	 * @throws DAOException
	 */
	boolean searchSimilarLogin(String login) throws DAOException;

	/**
	 * Register user.
	 * 
	 * @param login
	 * @param password
	 * @param person
	 * @throws DAOException
	 */
	void registerPerson(String login, String password, Person person) throws DAOException;

	/**
	 * Update user profile.
	 * 
	 * @param person
	 * @throws DAOException
	 */
	void updateProfile(Person person) throws DAOException;

	/**
	 * Remove user account by id.
	 * 
	 * @param idUser
	 * @return
	 * @throws DAOException
	 */
	boolean removePersonByID(int idUser) throws DAOException;

	/**
	 * Authorize person.
	 * 
	 * @param login
	 * @param password
	 * @return
	 * @throws DAOException
	 */
	Person authorizePerson(String login, String password) throws DAOException;
	

}
