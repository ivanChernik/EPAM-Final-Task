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

public class PersonDAOImpl implements IPersonDAO {

	private static final String SQL_UPDATE_PERSONAL_DATA_BY_ID = "UPDATE `hr-system`.`person` SET `name` =?, `surname`= ?, `middle_name`= ?, `date_of_birthday`= ?, `email`= ?, `phone`= ? WHERE `id_person` = ?;";

	private static final String SQL_DELETE_UDER_BY_ID = "DELETE FROM `hr-system`.`user` WHERE `id_user`= ?;";
	private static final String SQL_DELETE_PERSONAL_INFORMATION_BY_ID = "DELETE FROM `hr-system`.`person` WHERE `id_person`= ?;";

	private final static String SQL_ADD_PERSONS_DATA = "INSERT INTO `hr-system`.`person` (`name`, `surname`, `middle_name`, `date_of_birthday`, `email`, `phone`,`id_person`) VALUES (? ,?, ?, ?, ?, ?, ?);";
	private final static String SQL_ADD_NEW_APPLICANT = "INSERT INTO `hr-system`.`user` (`role`, `login`, `password`) VALUES (? ,?, ?);";

	private final static String SQL_SELECT_PERSON_BY_EMAIL = "SELECT `name`, `surname`, `middle_name`, `date_of_birthday`, `email`, `phone` FROM `hr-system`.`person` WHERE `email` = ? ;";
	private final static String SQL_SELECT_PERSONS_BY_NAMES = "SELECT `name`, `surname`, `middle_name`, `date_of_birthday`, `email`, `phone` FROM `hr-system`.`person` WHERE `name` = ? AND `surname` = ? AND `middle_name`=?;";
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
			return false;
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
			result = true;
			connection.commit();
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

	@Override
	public boolean updatePersonInformation(Person person) throws DAOException {
		ConnectionPool connectionPool = null;
		try {
			connectionPool = ConnectionPool.getInstance();
		} catch (ConnectionPoolException e) {
			log.fatal("Error connection pool instanse", e);
			throw new DAOException("Error connection pool instanse", e);
		}
		Connection connection = null;
		PreparedStatement updatePersonalDataPS = null;
		boolean result = false;
		try {
			connection = connectionPool.takeConnection();

			updatePersonalDataPS = connection.prepareStatement(SQL_UPDATE_PERSONAL_DATA_BY_ID);
			updatePersonalDataPS.setString(1, person.getName());
			updatePersonalDataPS.setString(2, person.getSurname());
			updatePersonalDataPS.setString(3, person.getMiddleName());
			updatePersonalDataPS.setDate(4, person.getDateOfBirthday());
			updatePersonalDataPS.setString(5, person.getEmail());
			updatePersonalDataPS.setString(6, person.getPhone());
			updatePersonalDataPS.setInt(7, person.getId());
			updatePersonalDataPS.executeUpdate();
			result = true;
		} catch (ConnectionPoolException | SQLException e) {
			log.error("Error updating profile data", e);
			throw new DAOException("Error updating profile data", e);

		} finally {
			try {
				updatePersonalDataPS.close();
			} catch (SQLException e) {
				log.error("Erorr closing statement", e);
			}
		}
		return result;
	}

	@Override
	public Person searchPersonByEmail(String email) throws DAOException {
		ConnectionPool connectionPool = null;
		try {
			connectionPool = ConnectionPool.getInstance();
		} catch (ConnectionPoolException e) {
			log.fatal("Error connection pool instanse", e);
			throw new DAOException("Error connection pool instanse", e);
		}
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;
		Person searchedPerson = null;
		try {

			connection = connectionPool.takeConnection();
			preparedStatement = connection.prepareStatement(SQL_SELECT_PERSON_BY_EMAIL);
			preparedStatement.setString(1, email);
			rs = preparedStatement.executeQuery();
			searchedPerson = getPerson(rs);
		} catch (ConnectionPoolException | SQLException e) {
			log.error("Error searching person by email", e);
			throw new DAOException(e);

		} finally {
			try {
				rs.close();
			} catch (SQLException e) {
				log.error("Error closing resultSet", e);
			}

			try {
				preparedStatement.close();
			} catch (SQLException e) {
				log.error("Error closing statement", e);
			}

			try {
				connection.close();
			} catch (SQLException e) {
				log.error("Error closing connection", e);
			}
		}
		return searchedPerson;
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
	public List<Person> searchPersonByNames(String name, String surname, String middleName) throws DAOException {
		ConnectionPool connectionPool = null;
		try {
			connectionPool = ConnectionPool.getInstance();
		} catch (ConnectionPoolException e) {
			log.fatal("Error connection pool instanse", e);
			throw new DAOException("Error connection pool instanse", e);
		}
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;
		List<Person> searchedPersonList = null;
		try {
			connection = connectionPool.takeConnection();
			preparedStatement = connection.prepareStatement(SQL_SELECT_PERSONS_BY_NAMES);
			preparedStatement.setString(1, name);
			preparedStatement.setString(2, surname);
			preparedStatement.setString(3, middleName);
			rs = preparedStatement.executeQuery();
			searchedPersonList = getPersonList(rs);
		} catch (ConnectionPoolException | SQLException e) {
			log.error("Error searching person by name", e);
			throw new DAOException("Error searching person by name", e);

		} finally {

			try {
				rs.close();
			} catch (SQLException e) {
				log.error("Error closing resultSet", e);
			}

			try {
				preparedStatement.close();
			} catch (SQLException e) {
				log.error("Error closing statement", e);
			}

			try {
				connection.close();
			} catch (SQLException e) {
				log.error("Error closing connection", e);
			}
		}
		return searchedPersonList;
	}

	private List<Person> getPersonList(ResultSet rs) throws SQLException {
		List<Person> personList = new ArrayList<Person>();
		while (rs.next()) {
			Person searchedPerson = new Person();
			searchedPerson.setName(rs.getString(SQL_NAME));
			searchedPerson.setSurname(rs.getString(SQL_SURNAME));
			searchedPerson.setMiddleName(rs.getString(SQL_MIDDLE_NAME));
			searchedPerson.setDateOfBirthday(rs.getDate(SQL_DATE_OF_BIRTHDAY));
			searchedPerson.setEmail(rs.getString(SQL_EMAIL));
			searchedPerson.setPhone(rs.getString(SQL_PHONE));
			personList.add(searchedPerson);
		}
		return personList;
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

}
