package by.epam.tc.hr_system.dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import by.epam.tc.hr_system.dao.IVacancyResponseDAO;
import by.epam.tc.hr_system.dao.connection_pool.ConnectionPool;
import by.epam.tc.hr_system.domain.Vacancy;
import by.epam.tc.hr_system.domain.VacancyResponse;
import by.epam.tc.hr_system.exception.ConnectionPoolException;
import by.epam.tc.hr_system.exception.DAOException;

/**
 * DAO implementation for response to vacancy.
 * 
 * @author Ivan Chernikau
 *
 */
public class VacancyResponseDAOImpl implements IVacancyResponseDAO {

	private static final String ID_RESPONCE = "id_responce";
	private static final String ID_VACANCY = "vacancy.id_vacancy";
	private static final String SQL_COMPANY_NAME = "company_name";
	private static final String SQL_VACANCY_NAME = "vacancy.name";
	private static final String SQL_STATUS = "status";
	private static final String SQL_SURNAME = "surname";
	private static final String SQL_NAME = "name";
	private static final String SQL_RESUME_INFO_PHONE = "resume_info.phone";
	private static final String SQL_POSITION = "position";
	private static final String SQL_ID_APPLICANT = "applicants_vacancies.id_applicant";
	private static final String SQL_APPLICATION_VACANCY_DATE = "applicants_vacancies.date";

	private static final String SQL_SELECT_RESPONCE_BY_VACANCY_ID = "SELECT DISTINCT id_responce,name, surname,applicants_vacancies.id_applicant,position, resume_info.phone, status, applicants_vacancies.date FROM `hr-system`.resume_info JOIN `hr-system`.applicants_vacancies ON resume_info.id_applicant = applicants_vacancies.id_applicant JOIN `hr-system`.person ON applicants_vacancies.id_applicant = person.id_person WHERE id_vacancy = ?;";
	private static final String SQL_SELECT_APPLICANT_RESPONCE = "SELECT vacancy.`id_vacancy`, `applicants_vacancies`.`status`, `applicants_vacancies`.`date`, `company_name`, `vacancy`.`name` FROM `hr-system`.applicants_vacancies INNER JOIN `hr-system`.vacancy ON `vacancy`.id_vacancy = `applicants_vacancies`.id_vacancy WHERE id_applicant = ?;";
	private static final String SQL_SELECT_RESPONCE_TO_VACANCY_FOR_APPLICANT = "SELECT * FROM `hr-system`.applicants_vacancies WHERE id_vacancy = ? AND id_applicant = ?;";
	
	private static final String SQL_ADD_RESPONCE_TO_VACANCY = "INSERT INTO `hr-system`.`applicants_vacancies` (`id_applicant`, `id_vacancy`, `status`, `date`) VALUES (?, ?, ?, ?);";
	
	private static final String SQL_UPDATE_RESPONCE_STATUS = "UPDATE `hr-system`.`applicants_vacancies` SET `status`=? WHERE `id_responce`=?;";

	private static final Logger log = Logger.getLogger(VacancyResponseDAOImpl.class);

	@Override
	public void addResponceToVacancy(VacancyResponse vacancyResponce) throws DAOException {
		ConnectionPool connectionPool = null;
		try {
			connectionPool = ConnectionPool.getInstance();
		} catch (ConnectionPoolException e) {
			log.fatal("Error connection pool instanse", e);
			throw new DAOException("Error connection pool instanse", e);
		}
		Connection connection = null;
		PreparedStatement addVacancyResponcePS = null;

		try {
			connection = connectionPool.takeConnection();
			addVacancyResponcePS = connection.prepareStatement(SQL_ADD_RESPONCE_TO_VACANCY);
			addVacancyResponcePS.setInt(1, vacancyResponce.getResume().getId());
			addVacancyResponcePS.setInt(2, vacancyResponce.getVacancy().getId());
			addVacancyResponcePS.setString(3, vacancyResponce.getStatus());
			addVacancyResponcePS.setDate(4, vacancyResponce.getDate());
			addVacancyResponcePS.executeUpdate();
			
		} catch (SQLException | ConnectionPoolException e) {
			log.error("Error addiction responce to vacancy", e);
			throw new DAOException("Error addiction responce to vacancy", e);
		}

		finally {
			try {
				addVacancyResponcePS.close();
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
	public List<VacancyResponse> getResponcesForApplicant(int idApplicant) throws DAOException {
		List<VacancyResponse> responceList = null;
		ConnectionPool connectionPool = null;
		try {
			connectionPool = ConnectionPool.getInstance();
		} catch (ConnectionPoolException e) {
			log.fatal("Error connection pool instanse", e);
			throw new DAOException("Error connection pool instanse", e);
		}
		Connection connection = null;
		PreparedStatement searchResponcePS = null;
		try {
			connection = connectionPool.takeConnection();
			searchResponcePS = connection.prepareStatement(SQL_SELECT_APPLICANT_RESPONCE);
			searchResponcePS.setInt(1, idApplicant);
			responceList = getResponceListForApplicant(searchResponcePS.executeQuery());

		} catch (SQLException | ConnectionPoolException e) {
			log.error("Error getting applicant responces", e);
			throw new DAOException("Error getting applicant responces", e);
		}

		finally {
			try {
				searchResponcePS.close();
			} catch (SQLException e) {
				log.error("Error closing statements", e);
			}

			try {
				connection.close();
			} catch (SQLException e) {
				log.error("Error closing connection", e);
			}
		}

		return responceList;
	}

	private List<VacancyResponse> getResponceListForApplicant(ResultSet rs) throws SQLException {
		List<VacancyResponse> responceList = new ArrayList<VacancyResponse>();

		while (rs.next()) {
			VacancyResponse responce = new VacancyResponse();
			responce.setStatus(rs.getString(SQL_STATUS));
			responce.setDate((rs.getDate(SQL_APPLICATION_VACANCY_DATE)));
			responce.getVacancy().setId((rs.getInt(ID_VACANCY)));
			responce.getVacancy().setCompanyName((rs.getString(SQL_COMPANY_NAME)));
			responce.getVacancy().setName((rs.getString(SQL_VACANCY_NAME)));
			responceList.add(responce);
		}
		return responceList;

	}

	@Override
	public List<VacancyResponse> getResponcesForVacancy(int idVacancy) throws DAOException {
		List<VacancyResponse> responceList = null;
		ConnectionPool connectionPool = null;
		try {
			connectionPool = ConnectionPool.getInstance();
		} catch (ConnectionPoolException e) {
			log.fatal("Error connection pool instanse", e);
			throw new DAOException("Error connection pool instanse", e);
		}
		Connection connection = null;
		PreparedStatement searchResponcesPS = null;
		try {
			connection = connectionPool.takeConnection();
			searchResponcesPS = connection.prepareStatement(SQL_SELECT_RESPONCE_BY_VACANCY_ID);
			searchResponcesPS.setInt(1, idVacancy);
			responceList = getResponceListForVacancy(searchResponcesPS.executeQuery());

		} catch (SQLException | ConnectionPoolException e) {
			log.error("Error getting responces for vacancy", e);
			throw new DAOException("Error getting responces for vacancy", e);
		}

		finally {
			try {
				searchResponcesPS.close();
			} catch (SQLException e) {
				log.error("Error closing statements", e);
			}

			try {
				connection.close();
			} catch (SQLException e) {
				log.error("Error closing connection", e);
			}
		}

		return responceList;
	}

	private List<VacancyResponse> getResponceListForVacancy(ResultSet rs) throws SQLException {
		List<VacancyResponse> responceList = new ArrayList<VacancyResponse>();

		while (rs.next()) {
			VacancyResponse responce = new VacancyResponse();
			responce.setId(rs.getInt(ID_RESPONCE));
			responce.setStatus(rs.getString(SQL_STATUS));
			responce.setDate((rs.getDate(SQL_APPLICATION_VACANCY_DATE)));
			responce.getResume().setId(rs.getInt(SQL_ID_APPLICANT));
			responce.getResume().setPosition(rs.getString(SQL_POSITION));
			responce.getResume().getContactInfo().setPhone(rs.getString(SQL_RESUME_INFO_PHONE));
			responce.getResume().getPerson().setName(rs.getString(SQL_NAME));
			responce.getResume().getPerson().setSurname(rs.getString(SQL_SURNAME));
			responceList.add(responce);
		}
		return responceList;

	}

	@Override
	public void changeStatus(String status, int[] idResponceArray) throws DAOException {
		ConnectionPool connectionPool = null;
		try {
			connectionPool = ConnectionPool.getInstance();
		} catch (ConnectionPoolException e) {
			log.fatal("Error connection pool instanse", e);
			throw new DAOException("Error connection pool instanse", e);
		}
		Connection connection = null;
		PreparedStatement updateResponceStatusPS = null;

		try {
			connection = connectionPool.takeConnection();
			updateResponceStatusPS = connection.prepareStatement(
					SQL_UPDATE_RESPONCE_STATUS);
			updateResponceStatusPS.setString(1, status);

			for (int idResome : idResponceArray) {
				updateResponceStatusPS.setInt(2, idResome);
				updateResponceStatusPS.executeUpdate();
			}

		} catch (SQLException | ConnectionPoolException e) {
			log.error("Error changing responce status", e);
			throw new DAOException("Error changing responce status", e);
		}

		finally {
			try {
				updateResponceStatusPS.close();
			} catch (SQLException e) {
				log.error("Error closing connection or statements", e);
			}
			
			try {
				connection.close();
			} catch (SQLException e) {
				log.error("Error closing connection", e);
			}
		}
	}
	
	@Override
	public boolean checkResponceToVacancy(int idVacancy, int idApplicant) throws DAOException {

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
			searchHRVacancy = connection.prepareStatement(
					SQL_SELECT_RESPONCE_TO_VACANCY_FOR_APPLICANT);
			searchHRVacancy.setInt(1, idVacancy);
			searchHRVacancy.setInt(2, idApplicant);
			ResultSet rs = searchHRVacancy.executeQuery();

			if (rs.next())
				return true;

		} catch (SQLException | ConnectionPoolException e) {
			log.error("Error selection responce to vacancy by ID aplicant and vacancy", e);
			throw new DAOException("Error selection responce to vacancy by ID aplicant and vacancy", e);
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

}
