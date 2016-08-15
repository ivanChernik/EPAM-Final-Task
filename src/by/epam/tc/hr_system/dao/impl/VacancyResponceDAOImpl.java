package by.epam.tc.hr_system.dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import by.epam.tc.hr_system.dao.IVacancyResponceDAO;
import by.epam.tc.hr_system.dao.connection_pool.ConnectionPool;
import by.epam.tc.hr_system.domain.Vacancy;
import by.epam.tc.hr_system.domain.VacancyResponce;
import by.epam.tc.hr_system.exception.ConnectionPoolException;
import by.epam.tc.hr_system.exception.DAOException;

public class VacancyResponceDAOImpl implements IVacancyResponceDAO {
	
	private static final String SQL_COMPANY_NAME = "company_name";
	private static final String SQL_DATE = "date";
	private static final String SQL_STATUS = "status";
	private static final String SQL_SURNAME = "surname";
	private static final String SQL_NAME = "name";
	private static final String SQL_RESUME_INFO_PHONE = "resume_info.phone";
	private static final String SQL_POSITION = "position";
	private static final String SQL_ID_APPLICANT = "applicants_vakancies.id_applicant";
	private static final String SQL_APPLICATION_VACANCY_DATE = "applicants_vakancies.date";
	
	private static final String SELECT_RESPONCE_BY_VACANCY_ID = "SELECT DISTINCT name, surname,applicants_vakancies.id_applicant,position, resume_info.phone, status, applicants_vakancies.date FROM `hr-system`.resume_info JOIN `hr-system`.applicants_vakancies ON resume_info.id_applicant = applicants_vakancies.id_applicant JOIN `hr-system`.person ON applicants_vakancies.id_applicant = person.id_person WHERE id_vakancy = ?;";
	private static final String SQL_SELECT_APPLICANT_RESPONCE = "SELECT `applicants_vakancies`.`status`, `applicants_vakancies`.`date`, `company_name` FROM `hr-system`.applicants_vakancies INNER JOIN `hr-system`.vacancy ON `vacancy`.id_vacancy = `applicants_vakancies`.id_vakancy WHERE id_applicant = ?;";
	
	private static final String SQL_ADD_RESPONCE_TO_VACANCY = "INSERT INTO `hr-system`.`applicants_vakancies` (`id_applicant`, `id_vakancy`, `status`, `date`) VALUES (?, ?, ?, ?);";
	
	private static final Logger log = Logger.getLogger(VacancyResponceDAOImpl.class);

	@Override
	public void addResponceToVacancy(VacancyResponce vacancyResponce) throws DAOException {
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
				connection.close();
			} catch (SQLException e) {
				throw new DAOException("Error closing connection or statements", e);
			}
		}
	}
	
	@Override
	public List<VacancyResponce> getResponcesForApplicant(int idApplicant) throws DAOException {
		List<VacancyResponce> responceList = null;
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
	
	private List<VacancyResponce> getResponceListForApplicant(ResultSet rs) throws SQLException {
		List<VacancyResponce> responceList = new ArrayList<VacancyResponce>();

		while (rs.next()) {
			VacancyResponce responce = new VacancyResponce();
			responce.setStatus(rs.getString(SQL_STATUS));
			responce.setDate((rs.getDate(SQL_APPLICATION_VACANCY_DATE)));
			responce.getVacancy().setCompanyName((rs.getString(SQL_COMPANY_NAME)));
			responceList.add(responce);
		}
		return responceList;

	}

	@Override
	public List<VacancyResponce> getResponcesForVacancy(int idVacancy) throws DAOException {
		List<VacancyResponce> responceList = null;
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
			searchResponcesPS = connection.prepareStatement(SELECT_RESPONCE_BY_VACANCY_ID);
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
	
	private List<VacancyResponce> getResponceListForVacancy(ResultSet rs) throws SQLException {
		List<VacancyResponce> responceList = new ArrayList<VacancyResponce>();
	
		while (rs.next()) {
			VacancyResponce responce = new VacancyResponce();
			responce.setStatus(rs.getString(SQL_STATUS));
			responce.setDate((rs.getDate(SQL_APPLICATION_VACANCY_DATE)));
			responce.getResume().setId(rs.getInt(SQL_ID_APPLICANT));
			responce.getResume().setPosition(rs.getString(SQL_POSITION));
			responce.getResume().getContactInfo().setPhone(rs.getString(SQL_RESUME_INFO_PHONE));
			responce.getPerson().setName(rs.getString(SQL_NAME));
			responce.getPerson().setSurname(rs.getString(SQL_SURNAME));
			responceList.add(responce);
		}
		return responceList;

	}

}
