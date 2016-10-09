package by.epam.tc.hr_system.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.mysql.jdbc.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import by.epam.tc.hr_system.dao.IPersonDAO;
import by.epam.tc.hr_system.dao.connection_pool.ConnectionPool;
import by.epam.tc.hr_system.domain.Person;
import by.epam.tc.hr_system.exception.ConnectionPoolException;
import by.epam.tc.hr_system.exception.DAOException;

/**
 * DAO implementation for person.
 * 
 * @author Ivan Chernikau
 *
 */
public class PersonDAOImpl implements IPersonDAO {

	private static final String SQL_UPDATE_PERSON_BY_ID = "UPDATE `hr-system`.`person` SET `name`= ?, `surname`= ?, `middle_name`= ?, `date_of_birthday`= ?, `email`= ?, `phone`= ? WHERE `id_person`= ?;";

	private static final String SQL_DELETE_UDER_BY_ID = "DELETE FROM `hr-system`.`user` WHERE `id_user`= ?;";
	private static final String SQL_DELETE_PERSONAL_INFORMATION_BY_ID = "DELETE FROM `hr-system`.`person` WHERE `id_person`= ?;";

	private final static String SQL_ADD_PERSONS_DATA = "INSERT INTO `hr-system`.`person` (`name`, `surname`, `middle_name`, `date_of_birthday`, `email`, `phone`,`id_person`) VALUES (? ,?, ?, ?, ?, ?, ?);";
	private final static String SQL_ADD_NEW_APPLICANT = "INSERT INTO `hr-system`.`user` (`role`, `login`, `password`) VALUES (? ,?, ?);";

	
	private final static String SQL_SELECT_PERSON_BY_LOGIN = "SELECT * FROM `hr-system`.`user` WHERE `login` = ? ;";
	private static final String SQL_SELECT_USER_BY_LOGIN_AND_PASSWORD = "SELECT `id_user`,`role`,`name`, `surname`, `middle_name`, `date_of_birthday`, `email`, `phone` FROM `hr-system`.user INNER JOIN `hr-system`.person ON id_user = id_person WHERE login = ? AND password = ?;";

	private static final String SQL_ROLE = "role";
	private static final String SQL_ID_USER = "id_user";
	private final static String SQL_NAME = "name";
	private final static String SQL_SURNAME = "surname";
	private final static String SQL_MIDDLE_NAME = "middle_name";
	private final static String SQL_DATE_OF_BIRTHDAY = "date_of_birthday";
	private final static String SQL_EMAIL = "email";
	private final static String SQL_PHONE = "phone";

	private static final Logger log = Logger.getLogger(PersonDAOImpl.class);

	@Override
	public void registerPerson(String login, String password, Person person) throws DAOException {
		ConnectionPool connectionPool = null;
		try {
			connectionPool = ConnectionPool.getInstance();
		} catch (ConnectionPoolException e) {
			log.fatal("Error connection pool instanse", e);
			throw new DAOException("Error connection pool instanse", e);
		}

		Connection connection = null;
		PreparedStatement addUserDataPS = null;
		PreparedStatement addProfileDataPS = null;
		try {
			connection = connectionPool.takeConnection();
			connection.setAutoCommit(false);

			// first
			addUserDataPS = connection.prepareStatement(SQL_ADD_NEW_APPLICANT, Statement.RETURN_GENERATED_KEYS);

			addUserDataPS.setString(1, person.getRole());
			addUserDataPS.setString(2, login);
			addUserDataPS.setString(3, password);
			addUserDataPS.executeUpdate();

			try (ResultSet generatedKeys = addUserDataPS.getGeneratedKeys()) {
				if (generatedKeys.next()) {
					person.setId(generatedKeys.getInt(1));
				} else {
					log.error("Error creation user: didn't get ID");
					throw new SQLException("Error creation user: didn't get ID");
				}
			}

			// second
			addProfileDataPS = connection.prepareStatement(SQL_ADD_PERSONS_DATA);
			addProfileDataPS.setString(1, person.getName());
			addProfileDataPS.setString(2, person.getSurname());
			addProfileDataPS.setString(3, person.getMiddleName());
			addProfileDataPS.setDate(4, person.getDateOfBirthday());
			addProfileDataPS.setString(5, person.getEmail());
			addProfileDataPS.setString(6, person.getPhone());
			addProfileDataPS.setInt(7, person.getId());
			addProfileDataPS.executeUpdate();

			connection.commit();
		}

		catch (ConnectionPoolException | SQLException e) {

			try {
				connection.rollback();
			} catch (SQLException eSQL) {
				log.fatal("Error rollback", eSQL);
				throw new DAOException("Fatal error rollback", e);
			}
			log.error("Error registration person", e);
			throw new DAOException("Error registration person", e);

		} finally {
			try {
				addUserDataPS.close();
			} catch (SQLException e) {
				log.error("Error closing statements", e);
			}

			try {
				addProfileDataPS.close();
			} catch (SQLException e) {
				log.error("Error closing statements", e);
			}

			try {
				connection.close();
			} catch (SQLException e) {
				log.error("Error closing connection", e);
			}

		}

	}

	public boolean searchSimilarLogin(String login) throws DAOException {
		ConnectionPool connectionPool = null;
		try {
			connectionPool = ConnectionPool.getInstance();
		} catch (ConnectionPoolException e) {
			log.fatal("Error connection pool instanse", e);
		}

		Connection connection = null;
		PreparedStatement searchLoginPS = null;
		ResultSet rs = null;
		try {
			connection = connectionPool.takeConnection();
			searchLoginPS = connection.prepareStatement(SQL_SELECT_PERSON_BY_LOGIN);
			searchLoginPS.setString(1, login);
			rs = searchLoginPS.executeQuery();
			return rs.next();

		} catch (ConnectionPoolException | SQLException e) {
			log.error("Error searching similar login", e);
			throw new DAOException("Error searching similar login", e);
		} finally {
			try {
				rs.close();
			} catch (SQLException e) {
				log.error("Error closing resultSet", e);
			}

			try {
				searchLoginPS.close();
			} catch (SQLException e) {
				log.error("Error closing statement", e);
			}

			try {
				connection.close();
			} catch (SQLException e) {
				log.error("Error closing connection", e);
			}
		}
	}

	@Override
	public boolean removePersonByID(int idUser) throws DAOException {
		ConnectionPool connectionPool = null;
		try {
			connectionPool = ConnectionPool.getInstance();
		} catch (ConnectionPoolException e) {
			log.fatal("Error connection pool instanse", e);
			throw new DAOException("Error connection pool instanse", e);
		}
		Connection connection = null;
		PreparedStatement removeUserPS = null;
		PreparedStatement removeProfileDataPS = null;
		boolean result = false;
		try {
			connection = connectionPool.takeConnection();
			connection.setAutoCommit(false);

			removeProfileDataPS = connection.prepareStatement(SQL_DELETE_PERSONAL_INFORMATION_BY_ID);
			removeProfileDataPS.setInt(1, idUser);
			removeProfileDataPS.executeUpdate();

			removeUserPS = connection.prepareStatement(SQL_DELETE_UDER_BY_ID);
			removeUserPS.setInt(1, idUser);
			removeUserPS.executeUpdate();
			connection.commit();
			result = true;
		} catch (SQLException | ConnectionPoolException e) {
			try {
				connection.rollback();
			} catch (SQLException eSQL) {
				log.fatal("Fatal error rollback", eSQL);
				throw new DAOException("Fatal error rollback", eSQL);
			}
			log.error("Error removing user", e);
			throw new DAOException("Error removing user", e);

		} finally {
			try {
				removeProfileDataPS.close();
			} catch (SQLException e) {
				log.error("Error closing statement", e);
			}

			try {
				removeUserPS.close();
			} catch (SQLException e) {
				log.error("Error closing statement", e);
			}

			try {
				connection.close();
			} catch (SQLException e) {
				log.error("Error closing connection", e);
			}

		}

		return result;
	}

	private Person getPerson(ResultSet rs) throws SQLException {
		Person person = new Person();

		if (!rs.next()) {
			return null;
		}

		person.setId(rs.getInt(SQL_ID_USER));
		person.setRole(rs.getString(SQL_ROLE));
		person.setName(rs.getString(SQL_NAME));
		person.setSurname(rs.getString(SQL_SURNAME));
		person.setMiddleName(rs.getString(SQL_MIDDLE_NAME));
		person.setDateOfBirthday(rs.getDate(SQL_DATE_OF_BIRTHDAY));
		person.setEmail(rs.getString(SQL_EMAIL));
		person.setPhone(rs.getString(SQL_PHONE));
		return person;
	}

	@Override
	public Person authorizePerson(String login, String password) throws DAOException {
		ConnectionPool connectionPool = null;
		try {
			connectionPool = ConnectionPool.getInstance();
		} catch (ConnectionPoolException e) {
			log.fatal("Error connection pool instanse", e);
			throw new DAOException("Error connection pool instanse", e);
		}
		Connection connection = null;
		PreparedStatement searchPersonPS = null;
		ResultSet rs = null;
		Person person = null;
		try {
			connection = connectionPool.takeConnection();
			searchPersonPS = connection.prepareStatement(SQL_SELECT_USER_BY_LOGIN_AND_PASSWORD);
			searchPersonPS.setString(1, login);
			searchPersonPS.setString(2, password);
			rs = searchPersonPS.executeQuery();
			person = getPerson(rs);
		} catch (ConnectionPoolException | SQLException e) {
			log.error("Error searching person by login and passowrd", e);
			throw new DAOException("Error searching person by login and passowrd", e);

		} finally {

			try {
				rs.close();
			} catch (SQLException e) {
				log.error("Error closing resultSet", e);
			}

			try {
				searchPersonPS.close();
			} catch (SQLException e) {
				log.error("Error closing statement", e);
			}

			try {
				connection.close();
			} catch (SQLException e) {
				log.error("Error closing connection", e);
			}

		}
		return person;
	}

	@Override
	public void updateProfile(Person person) throws DAOException {
		ConnectionPool connectionPool = null;
		try {
			connectionPool = ConnectionPool.getInstance();
		} catch (ConnectionPoolException e) {
			log.fatal("Error connection pool instanse", e);
			throw new DAOException("Error connection pool instanse", e);
		}

		Connection connection = null;
		PreparedStatement updateProfilePS = null;
		try {
			connection = connectionPool.takeConnection();

			updateProfilePS = connection.prepareStatement(SQL_UPDATE_PERSON_BY_ID);
			updateProfilePS.setString(1, person.getName());
			updateProfilePS.setString(2, person.getSurname());
			updateProfilePS.setString(3, person.getMiddleName());
			updateProfilePS.setDate(4, person.getDateOfBirthday());
			updateProfilePS.setString(5, person.getEmail());
			updateProfilePS.setString(6, person.getPhone());
			updateProfilePS.setInt(7, person.getId());
			updateProfilePS.executeUpdate();
		} catch (ConnectionPoolException | SQLException e) {
			log.error("Error update profile person", e);
			throw new DAOException("Error update profile person", e);
		} finally {
			try {
				updateProfilePS.close();
			} catch (SQLException e) {
				log.error("Error closing statements", e);
			}

			try {
				connection.close();
			} catch (SQLException e) {
				log.error("Error closing connection", e);
			}
		}

	}

}
