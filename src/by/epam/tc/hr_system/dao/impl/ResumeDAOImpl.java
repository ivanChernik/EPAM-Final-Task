package by.epam.tc.hr_system.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import by.epam.tc.hr_system.dao.IResumeDAO;
import by.epam.tc.hr_system.dao.connection_pool.ConnectionPool;
import by.epam.tc.hr_system.domain.ApplicantContactInfo;
import by.epam.tc.hr_system.domain.Education;
import by.epam.tc.hr_system.domain.PreviousPosition;
import by.epam.tc.hr_system.domain.Resume;
import by.epam.tc.hr_system.exception.ConnectionPoolException;
import by.epam.tc.hr_system.exception.DAOException;

public class ResumeDAOImpl implements IResumeDAO {

	private static final String SQL_ADD_RESUME_INFO = "INSERT INTO `hr-system`.`resume_info` (`id_applicant`, `skill`, `position`, `professional_info`, `photo_path`, `google_plus_link`, `linkedin_link`, `twitter_link`, `facebook_link`, `phone`, `email`, `address`) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
	private static final String SQL_ADD_EDUCATION = "INSERT INTO `hr-system`.`education` (`id_candidate`, `institution`, `department`, `speciality`, `form_of_education`, `date_of_entry`, `date_of_graduation`, `description`) VALUES (?, ?, ?, ?, ?, ?, ? ,?);";
	private static final String SQL_ADD_PREVIOUS_POSITION = "INSERT INTO `hr-system`.`experience` (`position`, `description`, `date_of_beginning`, `date_of_completion`, `id_applicant`) VALUES (?, ?, ?, ?, ?);";
	private static final Logger log = Logger.getLogger(ResumeDAOImpl.class);

	@Override
	public void addResume(Resume resume, int idUser) throws DAOException {
		ConnectionPool connectionPool = null;
		try {
			connectionPool = ConnectionPool.getInstance();
		} catch (ConnectionPoolException e) {
			log.fatal("Error connection pool instanse", e);
			throw new DAOException("Error connection pool instanse", e);
		}

		Connection connection = null;
		PreparedStatement addExperiencePS = null;
		PreparedStatement addEducationPS = null;
		PreparedStatement addResumeInfoPS = null;
		try {
			connection = connectionPool.takeConnection();
			connection.setAutoCommit(false);

			// first
			addExperiencePS = connection.prepareStatement(SQL_ADD_PREVIOUS_POSITION);

			PreviousPosition prevPosition = resume.getPreviousWorkList().get(0);

			addExperiencePS.setString(1, prevPosition.getPreviousPosition());
			addExperiencePS.setString(2, prevPosition.getWorkDescription());
			addExperiencePS.setDate(3, prevPosition.getWorkFrom());
			addExperiencePS.setDate(4, prevPosition.getWorkTo());
			addExperiencePS.setInt(5, idUser);
			addExperiencePS.executeUpdate();

			// second
			addEducationPS = connection.prepareStatement(SQL_ADD_EDUCATION);

			Education education = resume.getPreviousEducationList().get(0);

			addEducationPS.setInt(1, idUser);
			addEducationPS.setString(2, education.getUniversity());
			addEducationPS.setString(3, education.getFaculty());
			addEducationPS.setString(4, education.getSpecialty());
			addEducationPS.setString(5, education.getFormEducation());
			addEducationPS.setDate(6, education.getEducationFrom());
			addEducationPS.setDate(7, education.getEducationTo());
			addEducationPS.setString(8, education.getEducationDescription());
			addEducationPS.executeUpdate();

			// third
			addResumeInfoPS = connection.prepareStatement(SQL_ADD_RESUME_INFO);
			
			addResumeInfoPS.setInt(1, idUser);
			addResumeInfoPS.setString(2, resume.getSkill());
			addResumeInfoPS.setString(3, resume.getPostion());
			addResumeInfoPS.setString(4, resume.getProfInformation());
			addResumeInfoPS.setString(5, resume.getPathImage());
			
			ApplicantContactInfo contactInfo = resume.getContactInfo(); 
			
			addResumeInfoPS.setString(6, contactInfo.getLinkGooglePlus());
			addResumeInfoPS.setString(7, contactInfo.getLinkLinkedIn());
			addResumeInfoPS.setString(8, contactInfo.getLinkTwitter());
			addResumeInfoPS.setString(9, contactInfo.getLinkFacebook());
			addResumeInfoPS.setString(10, contactInfo.getPhone());
			addResumeInfoPS.setString(11, contactInfo.getEmail());
			addResumeInfoPS.setString(12, contactInfo.getAddress());
			addResumeInfoPS.executeUpdate();
			
			
			connection.commit();
		}

		catch (ConnectionPoolException | SQLException e) {
			log.error("Error resume person", e);
			try {
				connection.rollback();
			} catch (SQLException eSQL) {
				log.fatal("Error rollback", eSQL);
				throw new DAOException("Fatal error rollback", e);
			}
			throw new DAOException("Error addiction resume", e);

		} finally {
			try {
				addExperiencePS.close();
				addEducationPS.close();
				addResumeInfoPS.close();
				connection.close();
			} catch (SQLException e) {
				throw new DAOException("Error closing connection or statements", e);
			}
		}

	}

}
