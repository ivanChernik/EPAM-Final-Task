package by.epam.tc.hr_system.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import by.epam.tc.hr_system.dao.IVacancyDAO;
import by.epam.tc.hr_system.dao.connection_pool.ConnectionPool;
import by.epam.tc.hr_system.domain.Vacancy;
import by.epam.tc.hr_system.exception.ConnectionPoolException;
import by.epam.tc.hr_system.exception.DAOException;

public class VacancyDAOImpl implements IVacancyDAO {

	private static final String SQL_SELECT_COUNT_COMPANIES = "SELECT COUNT(distinct company) FROM `hr-system`.vacancy;";
	private static final String SQL_SELECT_COUNT_RESUMES = "SELECT COUNT(id_user) FROM `hr-system`.user WHERE `role` = 'applicant';";
	private static final String SQL_SELECT_COUNT_VACANCIES = "SELECT count(id_vacancy) FROM `hr-system`.vacancy;";
	private static final String SQL_ADD_NEW_VACANCY = "INSERT INTO `hr-system`.`vacancy` (`name`, `description`, `requirement`, `company`, `salary`,  `date_of_submission`, `status`, `id_hr`) VALUES (?, ?, ?, ?, ?, ?, ?,?);";
	private static final String SQL_DELETE_VACANCY = "DELETE FROM `hr-system`.`vacancy` WHERE  `id_vacancy`= ?;";
	private static final String SQL_UPDATE_VACANCY = "UPDATE `hr-system`.`vacancy` SET `name`= ?, `description`= ?, `requirement`=?, `company`= ?, `salary`= ?, `date_of_submission`=?, `status`=?, `id_hr`= ? WHERE `id_vacancy`= ?;";

	private static final Logger log = Logger.getLogger(VacancyDAOImpl.class);

	@Override
	public boolean addVacancy(Vacancy vacancy, int idHR) throws DAOException {
		ConnectionPool connectionPool = null;
		try {
			connectionPool = ConnectionPool.getInstance();
		} catch (ConnectionPoolException e) {
			log.fatal("Error connection pool instanse", e);
			throw new DAOException("Error connection pool instanse", e);
		}
		Connection connection = null;
		PreparedStatement addVacancyPS = null;

		boolean result = false;
		try {
			connection = connectionPool.takeConnection();
			addVacancyPS = connection.prepareStatement(SQL_ADD_NEW_VACANCY);

			addVacancyPS.setString(1, vacancy.getName());
			addVacancyPS.setString(2, vacancy.getDescrption());
			addVacancyPS.setString(3, vacancy.getRequirement());
			addVacancyPS.setString(4, vacancy.getCompany());
			addVacancyPS.setInt(5, vacancy.getSalary());
			addVacancyPS.setDate(6, vacancy.getDateSubmission());
			addVacancyPS.setString(7, vacancy.getStatus());
			addVacancyPS.setInt(8, idHR);

			addVacancyPS.executeUpdate();
			result = true;
		} catch (SQLException | ConnectionPoolException e) {
			log.error("Error adding vacancy", e);
			throw new DAOException("Error adding vacancy", e);
		}

		finally {
			try {
				addVacancyPS.close();
				connection.close();
			} catch (SQLException e) {
				throw new DAOException("Error closing connection or statements", e);
			}
		}
		return result;
	}

	@Override
	public boolean updateVacancy(Vacancy vacancy, int idHR) throws DAOException {
		ConnectionPool connectionPool = null;
		try {
			connectionPool = ConnectionPool.getInstance();
		} catch (ConnectionPoolException e) {
			log.fatal("Error connection pool instanse", e);
			throw new DAOException("Error connection pool instanse", e);
		}
		Connection connection = null;
		PreparedStatement updateVacancyPS = null;

		boolean result = false;
		try {
			connection = connectionPool.takeConnection();
			updateVacancyPS = connection.prepareStatement(SQL_UPDATE_VACANCY);

			updateVacancyPS.setString(1, vacancy.getName());
			updateVacancyPS.setString(2, vacancy.getDescrption());
			updateVacancyPS.setString(3, vacancy.getRequirement());
			updateVacancyPS.setString(4, vacancy.getCompany());
			updateVacancyPS.setInt(5, vacancy.getSalary());
			updateVacancyPS.setDate(6, vacancy.getDateSubmission());
			updateVacancyPS.setString(7, vacancy.getStatus());
			updateVacancyPS.setInt(8, idHR);
			updateVacancyPS.setInt(9, vacancy.getId());

			updateVacancyPS.executeUpdate();
			result = true;
		} catch (SQLException | ConnectionPoolException e) {
			log.error("Error updating vacancy", e);
			throw new DAOException("Error updating vacancy", e);
		}

		finally {
			try {
				updateVacancyPS.close();
				connection.close();
			} catch (SQLException e) {
				throw new DAOException("Error closing connection or statements", e);
			}
		}
		return result;
	}

	@Override
	public boolean removeVacancy(int idVacancy) throws DAOException {
		ConnectionPool connectionPool = null;
		try {
			connectionPool = ConnectionPool.getInstance();
		} catch (ConnectionPoolException e) {
			log.fatal("Error connection pool instanse", e);
			throw new DAOException("Error connection pool instanse", e);
		}
		Connection connection = null;
		PreparedStatement deketeVacancyPS = null;

		boolean result = false;
		try {
			connection = connectionPool.takeConnection();
			deketeVacancyPS = connection.prepareStatement(SQL_DELETE_VACANCY);

			deketeVacancyPS.setInt(1, idVacancy);

			deketeVacancyPS.executeUpdate();
			result = true;
		} catch (SQLException | ConnectionPoolException e) {
			log.error("Error deleting vacancy", e);
			throw new DAOException("Error deleting vacancy", e);
		}

		finally {
			try {
				deketeVacancyPS.close();
				connection.close();
			} catch (SQLException e) {
				throw new DAOException("Error closing connection or statements", e);
			}
		}
		return result;
	}

	@Override
	public int getCountVacancies() throws DAOException {
		int countVacancies = 0;
		ConnectionPool connectionPool = null;
		try {
			connectionPool = ConnectionPool.getInstance();
		} catch (ConnectionPoolException e) {
			log.fatal("Error connection pool instanse", e);
			throw new DAOException("Error connection pool instanse", e);
		}
		Connection connection = null;
		PreparedStatement countVacanciesPS = null;
		try {
			connection = connectionPool.takeConnection();
			countVacanciesPS = connection.prepareStatement(SQL_SELECT_COUNT_VACANCIES);
			countVacancies = getCountRows(countVacanciesPS.executeQuery());

		} catch (SQLException | ConnectionPoolException e) {
			log.error("Error computing count vacancies", e);
			throw new DAOException("Error computing count vacancies", e);
		}

		finally {
			try {
				countVacanciesPS.close();
				connection.close();
			} catch (SQLException e) {
				throw new DAOException("Error closing connection or statements", e);
			}
		}

		return countVacancies;
	}

	@Override
	public int getCountResumes() throws DAOException {
		int countResumes = 0;
		ConnectionPool connectionPool = null;
		try {
			connectionPool = ConnectionPool.getInstance();
		} catch (ConnectionPoolException e) {
			log.fatal("Error connection pool instanse", e);
			throw new DAOException("Error connection pool instanse", e);
		}
		Connection connection = null;
		PreparedStatement countResunesPS = null;
		try {
			connection = connectionPool.takeConnection();
			countResunesPS = connection.prepareStatement(SQL_SELECT_COUNT_RESUMES);
			countResumes = getCountRows(countResunesPS.executeQuery());

		} catch (SQLException | ConnectionPoolException e) {
			log.error("Error computing count resumes", e);
			throw new DAOException("Error computing count resumes", e);
		}

		finally {
			try {
				countResunesPS.close();
				connection.close();
			} catch (SQLException e) {
				throw new DAOException("Error closing connection or statements", e);
			}
		}

		return countResumes;
	}

	@Override
	public int getCountCompanies() throws DAOException {
		int countCompanies = 0;
		ConnectionPool connectionPool = null;
		try {
			connectionPool = ConnectionPool.getInstance();
		} catch (ConnectionPoolException e) {
			log.fatal("Error connection pool instanse", e);
			throw new DAOException("Error connection pool instanse", e);
		}
		Connection connection = null;
		PreparedStatement countCompaniesPS = null;
		try {
			connection = connectionPool.takeConnection();
			countCompaniesPS = connection.prepareStatement(SQL_SELECT_COUNT_COMPANIES);
			countCompanies = getCountRows(countCompaniesPS.executeQuery());

		} catch (SQLException | ConnectionPoolException e) {
			log.error("Error computing count companies", e);
			throw new DAOException("Error computing count companies", e);
		}

		finally {
			try {
				countCompaniesPS.close();
				connection.close();
			} catch (SQLException e) {
				throw new DAOException("Error closing connection or statements", e);
			}
		}

		return countCompanies;
	}

	private int getCountRows(ResultSet rs) throws SQLException {
		int countResumes = -1;
		rs.next();
		countResumes = rs.getInt(1);// number of column
		return countResumes;
	}

}
