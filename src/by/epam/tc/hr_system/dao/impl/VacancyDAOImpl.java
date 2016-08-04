package by.epam.tc.hr_system.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import by.epam.tc.hr_system.dao.IVacancyDAO;
import by.epam.tc.hr_system.dao.connection_pool.ConnectionPool;
import by.epam.tc.hr_system.domain.Vacancy;
import by.epam.tc.hr_system.exception.ConnectionPoolException;
import by.epam.tc.hr_system.exception.DAOException;

public class VacancyDAOImpl implements IVacancyDAO {

	private static final String SQL_SHORT_DESCRIPTION = "short_description";
	private static final String SQL_TYPE_EMPLOYMENT = "type_employment";
	private static final String SQL_CONTACT_INFORMATION = "contact_information";
	private static final String SQL_COMPANY_NAME = "company_name";
	private static final String SQL_STATUS = "status";
	private static final String SQL_DATE_OF_SUBMISSION = "date_of_submission";
	private static final String SQL_SALARY = "salary";
	private static final String SQL_REQUIREMENT = "requirement";
	private static final String SQL_DESCRIPTION = "description";
	private static final String SQL_NAME = "name";
	private static final String SQL_ID_VACANCY = "id_vacancy";
	private static final String SQL_SELECT_COUNT_COMPANIES = "SELECT COUNT(distinct company_name) FROM `hr-system`.vacancy;";
	private static final String SQL_SELECT_COUNT_VACANCIES = "SELECT count(id_vacancy) FROM `hr-system`.vacancy;";
	private static final String SQL_SELECT_HR_VACANCY = "SELECT `name`, `description`, `requirement`, `salary`, `date_of_submission`, `status`, `company_name`, `contact_information`, `type_employment`,`short_description` FROM `hr-system`.`vacancy` WHERE `id_hr` = ?;";
	private static final String SQL_SELECT_TOP_VACANCY = "SELECT `id_vacancy`,`name`, `description`, `requirement`, `salary`, `date_of_submission`, `status`, `company_name`, `contact_information`, `type_employment`,`short_description` FROM `hr-system`.`vacancy` WHERE `id_vacancy` in (1,2,3,4);";
	private static final String SQL_SELECT_VACANCY_BY_ID = "SELECT `id_vacancy`,`name`, `description`, `requirement`, `salary`, `date_of_submission`, `status`, `company_name`, `contact_information`, `type_employment`,`short_description` FROM `hr-system`.`vacancy` WHERE `id_vacancy` = ?;";

	private static final String SQL_ADD_NEW_VACANCY = "INSERT INTO `hr-system`.`vacancy` (`id_hr`, `name`, `description`, `requirement`, `salary`, `date_of_submission`, `status`, `company_name`, `contact_information`, `type_employment`,`short_description`) VALUES (?,?,?,?,?,?,?,?,?,?,?);";
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
			addVacancyPS.setInt(1, idHR);
			addVacancyPS.setString(2, vacancy.getName());
			addVacancyPS.setString(3, vacancy.getDescrption());
			addVacancyPS.setString(4, vacancy.getRequirement());
			addVacancyPS.setInt(5, vacancy.getSalary());
			addVacancyPS.setDate(6, vacancy.getDateSubmission());
			addVacancyPS.setString(7, vacancy.getStatus());
			addVacancyPS.setString(8, vacancy.getCompanyName());
			addVacancyPS.setString(9, vacancy.getContactInformation());
			addVacancyPS.setString(10, vacancy.getEmployment());
			addVacancyPS.setString(11, vacancy.getShortDescription());
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
			updateVacancyPS.setString(4, vacancy.getCompanyName());
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
	public List<Vacancy> getTopVacancies() throws DAOException {
		List<Vacancy> vacancyList = null;
		ConnectionPool connectionPool = null;
		try {
			connectionPool = ConnectionPool.getInstance();
		} catch (ConnectionPoolException e) {
			log.fatal("Error connection pool instanse", e);
			throw new DAOException("Error connection pool instanse", e);
		}
		Connection connection = null;
		PreparedStatement searchTopVacancyPS = null;
		try {
			connection = connectionPool.takeConnection();
			searchTopVacancyPS = connection.prepareStatement(SQL_SELECT_TOP_VACANCY);
			vacancyList = getVacancyList(searchTopVacancyPS.executeQuery());

		} catch (SQLException | ConnectionPoolException e) {
			log.error("Error get top vacancies", e);
			throw new DAOException("Error get top vacancies", e);
		}

		finally {
			try {
				searchTopVacancyPS.close();
				connection.close();
			} catch (SQLException e) {
				throw new DAOException("Error closing connection or statements", e);
			}
		}

		return vacancyList;
	}

	@Override
	public List<Vacancy> getHRVacancies(int idHR) throws DAOException {
		List<Vacancy> vacancyList = null;
		ConnectionPool connectionPool = null;
		try {
			connectionPool = ConnectionPool.getInstance();
		} catch (ConnectionPoolException e) {
			log.fatal("Error connection pool instanse", e);
			throw new DAOException("Error connection pool instanse", e);
		}
		Connection connection = null;
		PreparedStatement searchHRVacancyPS = null;
		try {
			connection = connectionPool.takeConnection();
			searchHRVacancyPS = connection.prepareStatement(SQL_SELECT_HR_VACANCY);
			vacancyList = getVacancyList(searchHRVacancyPS.executeQuery());

		} catch (SQLException | ConnectionPoolException e) {
			log.error("Error get HR vacancies", e);
			throw new DAOException("Error  get HR vacancies", e);
		}

		finally {
			try {
				searchHRVacancyPS.close();
				connection.close();
			} catch (SQLException e) {
				throw new DAOException("Error closing connection or statements", e);
			}
		}

		return vacancyList;
	}

	private List<Vacancy> getVacancyList(ResultSet rs) throws SQLException {
		List<Vacancy> vacancyList = new ArrayList<Vacancy>();

		while (rs.next()) {
			Vacancy vacancy = new Vacancy();
			vacancy.setId(rs.getInt(SQL_ID_VACANCY));
			vacancy.setName(rs.getString(SQL_NAME));
			vacancy.setDescrption(rs.getString(SQL_DESCRIPTION));
			vacancy.setShortDescription(rs.getString(SQL_SHORT_DESCRIPTION));
			vacancy.setRequirement(rs.getString(SQL_REQUIREMENT));
			vacancy.setSalary(rs.getInt(SQL_SALARY));
			vacancy.setDateSubmission(rs.getDate(SQL_DATE_OF_SUBMISSION));
			vacancy.setStatus(rs.getString(SQL_STATUS));
			vacancy.setCompanyName(rs.getString(SQL_COMPANY_NAME));
			vacancy.setContactInformation(rs.getString(SQL_CONTACT_INFORMATION));
			vacancy.setEmployment(rs.getString(SQL_TYPE_EMPLOYMENT));
			vacancyList.add(vacancy);
		}
		return vacancyList;

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

	@Override
	public Vacancy getVacancyByID(int vacancyId) throws DAOException {
		ConnectionPool connectionPool = null;
		try {
			connectionPool = ConnectionPool.getInstance();
		} catch (ConnectionPoolException e) {
			log.fatal("Error connection pool instanse", e);
			throw new DAOException("Error connection pool instanse", e);
		}
		Connection connection = null;
		PreparedStatement searchVacancyByID = null;
		Vacancy vacancy = null;
		try {
			connection = connectionPool.takeConnection();
			searchVacancyByID = connection.prepareStatement(SQL_SELECT_VACANCY_BY_ID);
			searchVacancyByID.setInt(1, vacancyId);
			vacancy = getVacancy(searchVacancyByID.executeQuery());

		} catch (SQLException | ConnectionPoolException e) {
			log.error("Error searching vacancy by ID", e);
			throw new DAOException("Error searching vacancy by ID", e);
		}

		finally {
			try {
				searchVacancyByID.close();
				connection.close();
			} catch (SQLException e) {
				throw new DAOException("Error closing connection or statements", e);
			}
		}

		return vacancy;
	}

	private Vacancy getVacancy(ResultSet rs) throws SQLException {
		rs.next();
		Vacancy vacancy = new Vacancy();
		vacancy.setId(rs.getInt(SQL_ID_VACANCY));
		vacancy.setName(rs.getString(SQL_NAME));
		vacancy.setDescrption(rs.getString(SQL_DESCRIPTION));
		vacancy.setShortDescription(rs.getString(SQL_SHORT_DESCRIPTION));
		vacancy.setRequirement(rs.getString(SQL_REQUIREMENT));
		vacancy.setSalary(rs.getInt(SQL_SALARY));
		vacancy.setDateSubmission(rs.getDate(SQL_DATE_OF_SUBMISSION));
		vacancy.setStatus(rs.getString(SQL_STATUS));
		vacancy.setCompanyName(rs.getString(SQL_COMPANY_NAME));
		vacancy.setContactInformation(rs.getString(SQL_CONTACT_INFORMATION));
		vacancy.setEmployment(rs.getString(SQL_TYPE_EMPLOYMENT));

		return vacancy;

	}
}