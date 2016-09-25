package by.epam.tc.hr_system.dao.impl;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import by.epam.tc.hr_system.dao.IPersonDAO;
import by.epam.tc.hr_system.dao.connection_pool.ConnectionPool;
import by.epam.tc.hr_system.dao.impl.PersonDAOImpl;
import by.epam.tc.hr_system.domain.Person;
import by.epam.tc.hr_system.exception.ConnectionPoolException;
import by.epam.tc.hr_system.exception.DAOException;

public class PersonDAOTest {
	private IPersonDAO personDAO;
	private ConnectionPool connectionPool;

	@Before
	public void initPersonDAO() {
		try {
            connectionPool = ConnectionPool.getInstance();
            connectionPool.initConnectionPool();
        } catch (ConnectionPoolException e) {
            e.printStackTrace();
        }
		personDAO = new PersonDAOImpl();
	}

	@After
	public void destroyPersonDAO() {
		personDAO = null;
		
		try {
            connectionPool.dispose();
        } catch (ConnectionPoolException e) {
            e.printStackTrace();
        }
	}
	
	
	@Test
	public void searchSimilarLoginTest() {
		boolean  result = false;
		String login = "galya";
		try {
			result = personDAO.searchSimilarLogin(login);
		} catch (DAOException e) {
			e.printStackTrace();
		}
		org.junit.Assert.assertTrue("Search similar login",
				result);
	}


	@Test
	public void authorizePersonTest() {
		
		String login = "kate";
		String password = "k";
		Person person = new Person();
		try {
			person = personDAO.authorizePerson(login, password);
		} catch (DAOException e) {
			e.printStackTrace();
		}
		org.junit.Assert.assertEquals(5, person.getId());
	}

}
