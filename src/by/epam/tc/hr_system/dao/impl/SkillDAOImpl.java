package by.epam.tc.hr_system.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;

import by.epam.tc.hr_system.dao.ISkillDAO;
import by.epam.tc.hr_system.dao.connection_pool.ConnectionPool;
import by.epam.tc.hr_system.domain.Skill;
import by.epam.tc.hr_system.exception.ConnectionPoolException;
import by.epam.tc.hr_system.exception.DAOException;

public class SkillDAOImpl implements ISkillDAO {
	
	private static final String SQL_DELETE_SKILL = "DELETE FROM `hr-system`.`skill` WHERE  `name`= ?;";
	private static final String SQL_ADD_NEW_SKILL = "INSERT INTO `hr-system`.`skill` (`name`) VALUES ( ?);";

	private static final Logger log = Logger.getLogger(SkillDAOImpl.class);
	private ConnectionPool connectionPool;

	@Override
	public boolean addNewSkill(String skillName) throws DAOException {
		
		try {
			connectionPool = ConnectionPool.getInstance();
		} catch (ConnectionPoolException e) {
			log.fatal("Fatal error connection pool instanse", e);
			throw new DAOException("Fatal error connection pool instanse", e);
		}
		Connection connection = null;
		PreparedStatement addSkillPS = null;

		boolean result = false;
		try {
			connection = connectionPool.takeConnection();
			addSkillPS = connection.prepareStatement(SQL_ADD_NEW_SKILL);

			addSkillPS.setString(1, skillName);

			addSkillPS.executeUpdate();
			result = true;
		} catch (SQLException | ConnectionPoolException e) {
			log.error("Error addiction new skill", e);
			throw new DAOException("Error addiction new skill", e);
		}

		finally {
			try {
				addSkillPS.close();
				connection.close();
			} catch (SQLException e) {
				throw new DAOException("Error closing connection or statements",
						e);
			}
		}
		return result;
	}

	@Override
	public boolean removeSkill(String nameSkill) throws DAOException {
		
		try {
			connectionPool = ConnectionPool.getInstance();
		} catch (ConnectionPoolException e) {
			log.fatal("Fatal error connection pool instanse", e);
			throw new DAOException("Fatal error connection pool instanse", e);
		}
		Connection connection = null;
		PreparedStatement removeSkillPS = null;

		boolean result = false;
		try {
			connection = connectionPool.takeConnection();
			removeSkillPS = connection.prepareStatement(SQL_DELETE_SKILL);

			removeSkillPS.setString(1, nameSkill);

			removeSkillPS.executeUpdate();
			result = true;
		} catch (SQLException | ConnectionPoolException e) {
			log.error("Error removing skill", e);
			throw new DAOException("Error removing skill", e);
		}

		finally {
			try {
				removeSkillPS.close();
				connection.close();
			} catch (SQLException e) {
				throw new DAOException("Error closing connection or statements",
						e);
			}
		}
		return result;
	}

	@Override
	public List<Skill> getSkillsByIDApplicant(int applicantID)
			throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}

}
