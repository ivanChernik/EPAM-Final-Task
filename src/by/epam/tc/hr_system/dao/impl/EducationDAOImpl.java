package by.epam.tc.hr_system.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import by.epam.tc.hr_system.dao.IEducationDAO;
import by.epam.tc.hr_system.dao.connection_pool.ConnectionPool;
import by.epam.tc.hr_system.domain.Education;
import by.epam.tc.hr_system.exception.ConnectionPoolException;
import by.epam.tc.hr_system.exception.DAOException;

public class EducationDAOImpl implements IEducationDAO {


	private static final String SQL_UPDATE_EDUCATION = "UPDATE `hr-system`.`education` SET `id_candidate`=?,`institution`= ?, `department`=?, `speciality`= ?, `form_of_education`= ?, `date_of_entry`= ?, `date_of_graduation`= ? WHERE `id_education`= ?;";
	private static final String SQL_ADD_EDUCATION_TO_CANDIDATE = "INSERT INTO `hr-system`.`education` (`id_candidate`,`institution`, `department`, `speciality`, `form_of_education`, `date_of_entry`, `date_of_graduation`) VALUES (?, ? , ?, ?, ? , ?, ?);";
	private static final String SQL_DELETE_EDUCATION = "DELETE FROM `hr-system`.`education` WHERE `id_education`= ?;";
	
	private static final Logger log = Logger.getLogger(EducationDAOImpl.class);
	private ConnectionPool connectionPool;

	@Override
	public boolean addEduction(Education eduction) throws DAOException {
		try {
			connectionPool = ConnectionPool.getInstance();
		} catch (ConnectionPoolException e) {
			log.fatal("Error connection pool instanse", e);
			throw new DAOException("Error connection pool instanse", e);
		}
		Connection connection = null;
		PreparedStatement addEducationPS = null;

		boolean result = false;
		try {
			connection = connectionPool.takeConnection();
			addEducationPS = connection.prepareStatement(SQL_ADD_EDUCATION_TO_CANDIDATE);
			
			addEducationPS.setInt(1, eduction.getIdPerson());
			addEducationPS.setString(2,eduction.getInstitution());
			addEducationPS.setString(3,eduction.getDepartament());
			addEducationPS.setString(4,eduction.getSpecialty());
			addEducationPS.setString(5,eduction.getFormEduction());
			addEducationPS.setDate(6,eduction.getDateEntry());
			addEducationPS.setDate(7,eduction.getDateGraduation());
			addEducationPS.executeUpdate();
			result = true;
		} catch (SQLException | ConnectionPoolException e) {
			log.error("Error adding new education", e);
			throw new DAOException("Error adding new education", e);
		}

		finally {
			try {
				addEducationPS.close();
				connection.close();
			} catch (SQLException e) {
				log.fatal("Error closing connection or statements", e);
				throw new DAOException("Error closing connection or statements",
						e);
			}
		}
		return result;
	}

	@Override
	public boolean removeEduction(int idEduction) throws DAOException {
		
		try {
			connectionPool = ConnectionPool.getInstance();
		} catch (ConnectionPoolException e) {
			log.fatal("Error connection pool instanse", e);
			throw new DAOException("Error connection pool instanse", e);
		}
		Connection connection = null;
		PreparedStatement removeEducationPS = null;

		boolean result = false;
		try {
			connection = connectionPool.takeConnection();

			removeEducationPS = connection.prepareStatement(SQL_DELETE_EDUCATION);
			removeEducationPS.setInt(1, idEduction);
			removeEducationPS.executeUpdate();
			result = true;
			
		} catch (SQLException | ConnectionPoolException e) {
	
			log.error("Error removing education", e);
			throw new DAOException("Error removing education", e);

		} finally {
			try {
				removeEducationPS.close();
				connection.close();
			} catch (SQLException e) {
				log.fatal("Error closing connection or statements", e);
				throw new DAOException("Error closing connection or statements",
						e);
			}

		}

		return result;
	}

	@Override
	public boolean updateEduction(Education eduction) throws DAOException {
		
		try {
			connectionPool = ConnectionPool.getInstance();
		} catch (ConnectionPoolException e) {
			log.fatal("Error connection pool instanse", e);
			throw new DAOException("Error connection pool instanse", e);
		}
		Connection connection = null;
		PreparedStatement updateEducationPS = null;

		boolean result = false;
		try {
			connection = connectionPool.takeConnection();
			updateEducationPS = connection.prepareStatement(SQL_UPDATE_EDUCATION);
			
			updateEducationPS.setInt(1, eduction.getIdPerson());
			updateEducationPS.setString(2,eduction.getInstitution());
			updateEducationPS.setString(3,eduction.getDepartament());
			updateEducationPS.setString(4,eduction.getSpecialty());
			updateEducationPS.setString(5,eduction.getFormEduction());
			updateEducationPS.setDate(6,eduction.getDateEntry());
			updateEducationPS.setDate(7,eduction.getDateGraduation());
			updateEducationPS.setInt(8,eduction.getId());
			updateEducationPS.executeUpdate();
			result = true;
		} catch (SQLException | ConnectionPoolException e) {
			log.error("Error updating education", e);
			throw new DAOException("Error updating education", e);
		}

		finally {
			try {
				updateEducationPS.close();
				connection.close();
			} catch (SQLException e) {
				log.fatal("Error closing connection or statements", e);
				throw new DAOException("Error closing connection or statements",
						e);
			}
		}
		return result;
	}

}
