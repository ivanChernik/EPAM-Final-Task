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

/**
 * DAO implementation for vacancy.
 * 
 * @author Ivan Chernikau
 *
 */
public class VacancyDAOImpl implements IVacancyDAO {

	private static final char PROCENT = '%';

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

	private static final String SQL_SELECT_VACANCY_BY_USER_PAREMETERS = "SELECT `id_vacancy`,`name`,`salary`,`short_description` FROM `hr-system`.vacancy WHERE `name` LIKE ? AND `salary` BETWEEN ? AND ? AND `type_employment` = ?;";
	private static final String SQL_SELECT_BY_ID_HR_AND_VACANCY = "SELECT * FROM `hr-system`.vacancy  WHERE id_vacancy = ? && id_hr = ?;";
	private static final String SQL_SELECT_COUNT_COMPANIES = "SELECT COUNT(distinct company_name) FROM `hr-system`.vacancy;";
	private static final String SQL_SELECT_AMOUNT_VACANCIES = "SELECT count(id_vacancy) FROM `hr-system`.vacancy;";
	private static final String SQL_SELECT_HR_VACANCY = "SELECT `id_vacancy`,`name`,`company_name`, `date_of_submission`,`status` FROM `hr-system`.`vacancy` WHERE `id_hr` = ?;";
	private static final String SQL_SELECT_TOP_VACANCY = "SELECT `id_vacancy`,`name`, `salary`, `short_description` FROM `hr-system`.`vacancy`";
	private static final String SQL_SELECT_VACANCY_BY_ID = "SELECT `id_vacancy`,`name`, `description`, `requirement`, `salary`, `date_of_submission`, `status`, `company_name`, `contact_information`, `type_employment`,`short_description` FROM `hr-system`.`vacancy` WHERE `id_vacancy` = ?;";

	private static final String SQL_ADD_NEW_VACANCY = "INSERT INTO `hr-system`.`vacancy` (`id_hr`, `name`, `description`, `requirement`, `salary`, `date_of_submission`, `status`, `company_name`, `contact_information`, `type_employment`,`short_description`) VALUES (?,?,?,?,?,?,?,?,?,?,?);";

	private static final String SQL_DELETE_VACANCY_BY_ID = "DELETE FROM `hr-system`.`vacancy` WHERE `id_vacancy`=?;";

	private static final String SQL_UPDATE_VACANCY_BY_ID = "UPDATE `hr-system`.`vacancy` SET `name`= ?, `short_description`= ?, `description`= ?, `requirement`= ?, `salary`= ?, `status`= ?, `company_name`= ?, `contact_information`= ?, `type_employment`= ? WHERE `id_vacancy`= ?;";

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
			addVacancyPS.setString(3, vacancy.getDescription());
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
			log.error("Error addiction vacancy", e);
			throw new DAOException("Error addiction vacancy", e);
		}

		finally {
			try {
				addVacancyPS.close();
			} catch (SQLException e) {
				log.error("Error closing statements", e);
			}

			try {
				connection.close();
			} catch (SQLException e) {
				log.error("Error closing connection or statements", e);
			}
		}
		return result;
	}

	@Override
	public void updateVacancy(Vacancy vacancy) throws DAOException {
		ConnectionPool connectionPool = null;
		try {
			connectionPool = ConnectionPool.getInstance();
		} catch (ConnectionPoolException e) {
			log.fatal("Error connection pool instanse", e);
			throw new DAOException("Error connection pool instanse", e);
		}
		Connection connection = null;
		PreparedStatement updateVacancyPS = null;

		try {
			connection = connectionPool.takeConnection();
			updateVacancyPS = connection.prepareStatement(SQL_UPDATE_VACANCY_BY_ID);
			updateVacancyPS.setString(1, vacancy.getName());
			updateVacancyPS.setString(2, vacancy.getShortDescription());
			updateVacancyPS.setString(3, vacancy.getDescription());
			updateVacancyPS.setString(4, vacancy.getRequirement());
			updateVacancyPS.setInt(5, vacancy.getSalary());
			updateVacancyPS.setString(6, vacancy.getStatus());
			updateVacancyPS.setString(7, vacancy.getCompanyName());
			updateVacancyPS.setString(8, vacancy.getContactInformation());
			updateVacancyPS.setString(9, vacancy.getEmployment());
			updateVacancyPS.setInt(10, vacancy.getId());
			updateVacancyPS.executeUpdate();

		} catch (SQLException | ConnectionPoolException e) {
			log.error("Error updating vacancy by id", e);
			throw new DAOException("Error updating vacancy by id", e);
		}

		finally {
			try {
				updateVacancyPS.close();
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
			deketeVacancyPS = connection.prepareStatement(SQL_DELETE_VACANCY_BY_ID);

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
			} catch (SQLException e) {
				log.error("Error closing statements", e);
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
		PreparedStatement searchCountVacancyPS = null;
		try {
			connection = connectionPool.takeConnection();

			searchCountVacancyPS = connection.prepareStatement(SQL_SELECT_AMOUNT_VACANCIES);
			ResultSet rsAmount = searchCountVacancyPS.executeQuery();
			rsAmount.next();
			int amountVacanciesRS = rsAmount.getInt(1);

			searchTopVacancyPS = connection.prepareStatement(SQL_SELECT_TOP_VACANCY);

			vacancyList = getTopVacancyList(amountVacanciesRS, searchTopVacancyPS.executeQuery());

		} catch (SQLException | ConnectionPoolException e) {
			log.error("Error get top vacancies", e);
			throw new DAOException("Error get top vacancies", e);
		}

		finally {
			try {
				searchTopVacancyPS.close();
			} catch (SQLException e) {
				log.error("Error closing statements", e);
			}

			try {
				connection.close();
			} catch (SQLException e) {
				log.error("Error closing connection", e);
			}
		}

		return vacancyList;
	}

	private List<Vacancy> getTopVacancyList(int amountVacanciesRS, ResultSet rs) throws SQLException {
		List<Vacancy> vacancyList = new ArrayList<Vacancy>();

		int amount = 9;
		if (amountVacanciesRS < 9) {
			amount = amountVacanciesRS;
		}
		while (rs.next() && amount > 0) {
			Vacancy vacancy = new Vacancy();
			vacancy.setId(rs.getInt(SQL_ID_VACANCY));
			vacancy.setName(rs.getString(SQL_NAME));
			vacancy.setShortDescription(rs.getString(SQL_SHORT_DESCRIPTION));
			vacancy.setSalary(rs.getInt(SQL_SALARY));
			vacancyList.add(vacancy);
			amount--;
		}
		return vacancyList;

	}

	private List<Vacancy> getFullVacancyList(ResultSet rs) throws SQLException {
		List<Vacancy> vacancyList = new ArrayList<Vacancy>();

		while (rs.next()) {
			Vacancy vacancy = new Vacancy();
			vacancy.setId(rs.getInt(SQL_ID_VACANCY));
			vacancy.setName(rs.getString(SQL_NAME));
			vacancy.setDescription(rs.getString(SQL_DESCRIPTION));
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
			searchHRVacancyPS.setInt(1, idHR);
			vacancyList = getHRVacancyList(searchHRVacancyPS.executeQuery());

		} catch (SQLException | ConnectionPoolException e) {
			log.error("Error get HR vacancies", e);
			throw new DAOException("Error  get HR vacancies", e);
		}

		finally {
			try {
				searchHRVacancyPS.close();
			} catch (SQLException e) {
				log.error("Error closing statements", e);
			}

			try {
				connection.close();
			} catch (SQLException e) {
				log.error("Error closing connection", e);
			}
		}

		return vacancyList;
	}

	private List<Vacancy> getHRVacancyList(ResultSet rs) throws SQLException {
		List<Vacancy> vacancyList = new ArrayList<Vacancy>();

		while (rs.next()) {
			Vacancy vacancy = new Vacancy();
			vacancy.setId(rs.getInt(SQL_ID_VACANCY));
			vacancy.setName(rs.getString(SQL_NAME));
			vacancy.setDateSubmission(rs.getDate(SQL_DATE_OF_SUBMISSION));
			vacancy.setStatus(rs.getString(SQL_STATUS));
			vacancy.setCompanyName(rs.getString(SQL_COMPANY_NAME));
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
			countVacanciesPS = connection.prepareStatement(SQL_SELECT_AMOUNT_VACANCIES);
			countVacancies = getCountRows(countVacanciesPS.executeQuery());

		} catch (SQLException | ConnectionPoolException e) {
			log.error("Error computing count vacancies", e);
			throw new DAOException("Error computing count vacancies", e);
		}

		finally {
			try {
				countVacanciesPS.close();
			} catch (SQLException e) {
				log.error("Error closing connection or statements", e);
			}

			try {
				connection.close();
			} catch (SQLException e) {
				log.error("Error closing connection", e);
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
			} catch (SQLException e) {
				log.error("Error closing statements", e);
			}

			try {
				connection.close();
			} catch (SQLException e) {
				log.error("Error closing connection", e);
			}
		}

		return countCompanies;
	}

	private int getCountRows(ResultSet rs) throws SQLException {
		int countResumes = -1;

		if (!rs.next()) {
			return 0;
		}

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
			} catch (SQLException e) {
				log.error("Error closing statements", e);
			}

			try {
				connection.close();
			} catch (SQLException e) {
				log.error("Error closing connection", e);
			}
		}

		return vacancy;
	}

	private Vacancy getVacancy(ResultSet rs) throws SQLException {

		Vacancy vacancy = new Vacancy();

		if (!rs.next()) {
			return null;
		}

		vacancy.setId(rs.getInt(SQL_ID_VACANCY));
		vacancy.setName(rs.getString(SQL_NAME));
		vacancy.setDescription(rs.getString(SQL_DESCRIPTION));
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

	@Override
	public void deleteHRVacancies(int[] idVacancyArray) throws DAOException {
		ConnectionPool connectionPool = null;
		try {
			connectionPool = ConnectionPool.getInstance();
		} catch (ConnectionPoolException e) {
			log.fatal("Error connection pool instanse", e);
			throw new DAOException("Error connection pool instanse", e);
		}
		Connection connection = null;
		PreparedStatement deleteHRvacancies = null;
		try {
			connection = connectionPool.takeConnection();
			deleteHRvacancies = connection.prepareStatement(SQL_DELETE_VACANCY_BY_ID);
			for (int idVacancy : idVacancyArray) {
				deleteHRvacancies.setInt(1, idVacancy);
				deleteHRvacancies.execute();
			}

		} catch (SQLException | ConnectionPoolException e) {
			log.error("Error deletion vacancy by ID", e);
			throw new DAOException("Error deletion vacancy by ID", e);
		}

		finally {
			try {
				deleteHRvacancies.close();
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

	@Override
	public boolean checkVacancyToHR(int idHR, int idVacancy) throws DAOException {
		ConnectionPool connectionPool = null;
		try {
			connectionPool = ConnectionPool.getInstance();
		} catch (ConnectionPoolException e) {
			log.fatal("Error connection pool instanse", e);
			throw new DAOException("Error connection pool instanse", e);
		}
		Connection connection = null;
		PreparedStatement searchHRVacancy = null;
		try {
			connection = connectionPool.takeConnection();
			searchHRVacancy = connection.prepareStatement(SQL_SELECT_BY_ID_HR_AND_VACANCY);
			searchHRVacancy.setInt(1, idVacancy);
			searchHRVacancy.setInt(2, idHR);
			ResultSet rs = searchHRVacancy.executeQuery();

			if (rs.next())
				return true;

		} catch (SQLException | ConnectionPoolException e) {
			log.error("Error selection vacancy by ID HR and Vacancy", e);
			throw new DAOException("Error selection vacancy by ID HR and Vacancy", e);
		}

		finally {
			try {
				searchHRVacancy.close();
			} catch (SQLException e) {
				log.error("Error closing statements", e);
			}

			try {
				connection.close();
			} catch (SQLException e) {
				log.error("Error closing connection", e);
			}
		}
		return false;
	}

	@Override
	public List<Vacancy> searchVacancyByParameters(String position, String employment, int salaryFrom, int salaryTo)
			throws DAOException {
		ConnectionPool connectionPool = null;
		try {
			connectionPool = ConnectionPool.getInstance();
		} catch (ConnectionPoolException e) {
			log.fatal("Error connection pool instanse", e);
			throw new DAOException("Error connection pool instanse", e);
		}
		Connection connection = null;
		PreparedStatement searchVacancyPS = null;
		List<Vacancy> vacancyList = null;

		try {
			connection = connectionPool.takeConnection();
			searchVacancyPS = connection.prepareStatement(SQL_SELECT_VACANCY_BY_USER_PAREMETERS);
			searchVacancyPS.setString(1, PROCENT + position + PROCENT);
			searchVacancyPS.setInt(2, salaryFrom);
			searchVacancyPS.setInt(3, salaryTo);
			searchVacancyPS.setString(4, employment);
			vacancyList = getShortVacancyList(searchVacancyPS.executeQuery());

		} catch (SQLException | ConnectionPoolException e) {
			log.error("Error searching vacancy by user parameters", e);
			throw new DAOException("Error searching vacancy by user parameters", e);
		}

		finally {
			try {
				searchVacancyPS.close();
			} catch (SQLException e) {
				log.error("Error closing statements", e);
			}

			try {
				connection.close();
			} catch (SQLException e) {
				log.error("Error closing connection", e);
			}
		}
		return vacancyList;
	}

	private List<Vacancy> getShortVacancyList(ResultSet rs) throws SQLException {
		List<Vacancy> vacancyList = new ArrayList<Vacancy>();

		while (rs.next()) {
			Vacancy vacancy = new Vacancy();
			vacancy.setId(rs.getInt(SQL_ID_VACANCY));
			vacancy.setName(rs.getString(SQL_NAME));
			vacancy.setShortDescription(rs.getString(SQL_SHORT_DESCRIPTION));
			vacancy.setSalary(rs.getInt(SQL_SALARY));
			vacancyList.add(vacancy);
		}
		return vacancyList;

	}

}