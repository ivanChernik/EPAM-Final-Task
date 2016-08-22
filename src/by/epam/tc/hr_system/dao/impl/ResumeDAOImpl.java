package by.epam.tc.hr_system.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import by.epam.tc.hr_system.dao.IResumeDAO;
import by.epam.tc.hr_system.dao.connection_pool.ConnectionPool;
import by.epam.tc.hr_system.domain.ApplicantContactInfo;
import by.epam.tc.hr_system.domain.Education;
import by.epam.tc.hr_system.domain.PreviousPosition;
import by.epam.tc.hr_system.domain.Resume;
import by.epam.tc.hr_system.domain.Vacancy;
import by.epam.tc.hr_system.exception.ConnectionPoolException;
import by.epam.tc.hr_system.exception.DAOException;

public class ResumeDAOImpl implements IResumeDAO {
	
	private static final String SQL_EDUCATION_KIND = "kind_education";
	private static final String SQL_EDUCATION_DESCRIPTION = "education.description";
	private static final String SQL_EDUCATION_DATE_OF_GRADUATION = "date_of_graduation";
	private static final String SQL_EDUCATION_DATE_OF_ENTRY = "date_of_entry";
	private static final String SQL_EDUCATION_FORM_OF_EDUCATION = "form_education";
	private static final String SQL_EDUCATION_SPECIALITY = "speciality";
	private static final String SQL_EDUCATION_DEPARTMENT = "department";
	private static final String SQL_EDUCATION_INSTITUTION = "institution";

	private static final String SQL_PREVIOUS_POSITION_DATE_OF_COMPLETION = "date_of_completion";
	private static final String SQL_PREVIOUS_POSITION_DATE_OF_BEGINNING = "date_of_beginning";
	private static final String SQL_PREVIOUS_POSITION_DESCRIPTION = "experience.description";
	private static final String SQL_PREVIOUS_POSITION_NAME = "experience.position";

	private static final String SQL_USER_SURNAME = "surname";
	private static final String SQL_USER_NAME = "name";
	private static final String SQL_USER_ADDRESS = "address";
	private static final String SQL_USER_EMAIL = "resume_info.email";
	private static final String SQL_USER_PHONE = "resume_info.phone";
	private static final String SQL_USER_FACEBOOK_LINK = "facebook_link";
	private static final String SQL_USER_TWITTER_LINK = "twitter_link";
	private static final String SQL_USER_LINKEDIN_LINK = "linkedin_link";
	private static final String SQL_USER_GOOGLE_PLUS_LINK = "google_plus_link";
	private static final String SQL_USER_PHOTO_PATH = "photo_path";
	private static final String SQL_USER_PROFESSIONAL_INFO = "professional_info";
	private static final String SQL_USER_PREFER_POSITION = "resume_info.position";
	private static final String SQL_USER_SKILL = "skill";

	private static final String SQL_ADD_RESUME_INFO = "INSERT INTO `hr-system`.`resume_info` (`id_applicant`, `skill`, `position`, `professional_info`, `photo_path`, `google_plus_link`, `linkedin_link`, `twitter_link`, `facebook_link`, `phone`, `email`, `address`) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
	private static final String SQL_ADD_EDUCATION = "INSERT INTO `hr-system`.`education` (`id_candidate`, `institution`, `department`, `speciality`, `form_education`, `date_of_entry`, `date_of_graduation`, `description`) VALUES (?, ?, ?, ?, ?, ?, ? ,?);";
	private static final String SQL_ADD_PREVIOUS_POSITION = "INSERT INTO `hr-system`.`experience` (`position`, `description`, `date_of_beginning`, `date_of_completion`, `id_applicant`) VALUES (?, ?, ?, ?, ?);";

	private static final String SQL_SELECT_RESUME_ID = "SELECT id_applicant FROM `hr-system`.resume_info WHERE id_applicant = ?;";
	private static final String SQL_SELECT_COUNT_RESUMES = "SELECT COUNT(id_applicant) FROM `hr-system`.resume_info;";
	private static final String SQL_SELECT_EDUCATION_BY_RESUME_ID = "SELECT DISTINCT  kind_education, education.description, date_of_graduation , `date_of_entry`, form_education, speciality, department, institution FROM `hr-system`.resume_info JOIN `hr-system`.education ON resume_info.id_applicant = education.id_candidate WHERE resume_info.`id_applicant`= ?;";
	private static final String SQL_SELECT_EXPERIENCE_BY_ID_RESUME = "SELECT DISTINCT experience.`position`, experience.`description`, date_of_beginning , date_of_completion FROM `hr-system`.resume_info  JOIN `hr-system`.experience ON resume_info.id_applicant = experience.id_applicant WHERE resume_info.`id_applicant`= ?;";
	private static final String SQL_SELECT_CONTACT_INFORMATION_BY_ID_RESUME = "SELECT DISTINCT `name`, `surname`,`skill`, resume_info.`position`, `professional_info`,`photo_path`,`google_plus_link`, `linkedin_link`, `twitter_link`, `facebook_link`, resume_info.`phone`, resume_info.`email`, `address` FROM `hr-system`.resume_info JOIN `hr-system`.person ON resume_info.id_applicant = person.id_person WHERE id_person = ?;";

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

			Education education = resume.getEducationList().get(0);

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
			addResumeInfoPS.setString(3, resume.getPosition());
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
			
			try {
				connection.rollback();
			} catch (SQLException eSQL) {
				log.fatal("Error rollback", eSQL);
				throw new DAOException("Fatal error rollback", e);
			}
			
			log.error("Error resume person", e);
			throw new DAOException("Error addiction resume", e);

		} finally {
			try {
				addExperiencePS.close();
			} catch (SQLException e) {
				log.error("Error closing statements", e);
			}
			try {
				addResumeInfoPS.close();
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
			} catch (SQLException e) {
				log.error("Error closing statements", e);
			}
			try {
				connection.close();
			} catch (SQLException e) {
				log.error("Error closing connection", e);
			}
		}

		return countResumes;
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
	public Resume getApplicantResume(int idResume) throws DAOException {

		ConnectionPool connectionPool = null;
		try {
			connectionPool = ConnectionPool.getInstance();
		} catch (ConnectionPoolException e) {
			log.fatal("Error connection pool instanse", e);
			throw new DAOException("Error connection pool instanse", e);
		}

		Connection connection = null;
		PreparedStatement checkResumePS = null;
		PreparedStatement getContactInfoPS = null;
		PreparedStatement getExperienceInfoPS = null;
		PreparedStatement getEducaionPS = null;

		Resume resume = null;

		try {
			connection = connectionPool.takeConnection();
			
			checkResumePS = connection.prepareStatement(SQL_SELECT_RESUME_ID);
			checkResumePS.setInt(1, idResume);
			if(!checkResumePS.executeQuery().next()){
				return null;
			}
			
			connection.setAutoCommit(false);
			
			// first
			getContactInfoPS = connection.prepareStatement(SQL_SELECT_CONTACT_INFORMATION_BY_ID_RESUME);
			getContactInfoPS.setInt(1, idResume);
			resume = getContactInfo(getContactInfoPS.executeQuery());

			// second
			getExperienceInfoPS = connection.prepareStatement(SQL_SELECT_EXPERIENCE_BY_ID_RESUME);
			getExperienceInfoPS.setInt(1, idResume);

			resume.setPreviousWorkList(getPreviousPositionList(getExperienceInfoPS.executeQuery()));

			// third
			getEducaionPS = connection.prepareStatement(SQL_SELECT_EDUCATION_BY_RESUME_ID);
			getEducaionPS.setInt(1, idResume);

			resume.setEducationList(getEducationList(getEducaionPS.executeQuery()));

			connection.commit();
		}

		catch (ConnectionPoolException | SQLException e) {
			
			try {
				connection.rollback();
			} catch (SQLException eSQL) {
				log.fatal("Error rollback", eSQL);
				throw new DAOException("Fatal error rollback", e);
			}
			
			log.error("Error getting resume person", e);
			throw new DAOException("Error addiction resume", e);

		} finally {
			
			try {
				checkResumePS.close();
			} catch (SQLException e) {
				log.error("Error closing statements", e);
			}
			
			try {
				getContactInfoPS.close();
			} catch (SQLException e) {
				log.error("Error closing statements", e);
			}

			try {
				getExperienceInfoPS.close();
			} catch (SQLException e) {
				log.error("Error closing statements", e);
			}

			try {
				getEducaionPS.close();
			} catch (SQLException e) {
				log.error("Error closing statements", e);
			}

			try {
				connection.close();
			} catch (SQLException e) {
				log.error("Error closing connection", e);
			}
		}

		return resume;
	}

	private Resume getContactInfo(ResultSet rs) throws SQLException {
		Resume resume = new Resume();
		ApplicantContactInfo contactinfo = new ApplicantContactInfo();

		if (!rs.next()) {
			return resume;
		}

		resume.getPerson().setName(rs.getString(SQL_USER_NAME));
		resume.getPerson().setSurname(rs.getString(SQL_USER_SURNAME));

		resume.setSkill(rs.getString(SQL_USER_SKILL));
		resume.setPosition(rs.getString(SQL_USER_PREFER_POSITION));
		resume.setProfInformation(rs.getString(SQL_USER_PROFESSIONAL_INFO));
		resume.setPathImage(rs.getString(SQL_USER_PHOTO_PATH));

		contactinfo.setLinkGooglePlus(rs.getString(SQL_USER_GOOGLE_PLUS_LINK));
		contactinfo.setLinkLinkedIn(rs.getString(SQL_USER_LINKEDIN_LINK));
		contactinfo.setLinkTwitter(rs.getString(SQL_USER_TWITTER_LINK));
		contactinfo.setLinkFacebook(rs.getString(SQL_USER_FACEBOOK_LINK));
		contactinfo.setPhone(rs.getString(SQL_USER_PHONE));
		contactinfo.setEmail(rs.getString(SQL_USER_EMAIL));
		contactinfo.setAddress(rs.getString(SQL_USER_ADDRESS));

		resume.setContactInfo(contactinfo);

		return resume;
	}

	private List<PreviousPosition> getPreviousPositionList(ResultSet rs) throws SQLException {
		List<PreviousPosition> previousPositionList = new ArrayList<PreviousPosition>();

		while (rs.next()) {
			PreviousPosition previousPosition = new PreviousPosition();

			previousPosition.setPreviousPosition(rs.getString(SQL_PREVIOUS_POSITION_NAME));
			previousPosition.setWorkDescription(rs.getString(SQL_PREVIOUS_POSITION_DESCRIPTION));
			previousPosition.setWorkFrom(rs.getDate(SQL_PREVIOUS_POSITION_DATE_OF_BEGINNING));
			previousPosition.setWorkTo(rs.getDate(SQL_PREVIOUS_POSITION_DATE_OF_COMPLETION));

			previousPositionList.add(previousPosition);

		}

		return previousPositionList;
	}

	private List<Education> getEducationList(ResultSet rs) throws SQLException {

		List<Education> educationList = new ArrayList<Education>();

		while (rs.next()) {

			Education education = new Education();

			education.setUniversity(rs.getString(SQL_EDUCATION_INSTITUTION));
			education.setFaculty(rs.getString(SQL_EDUCATION_DEPARTMENT));
			education.setSpecialty(rs.getString(SQL_EDUCATION_SPECIALITY));
			education.setFormEducation(rs.getString(SQL_EDUCATION_FORM_OF_EDUCATION));
			education.setEducationFrom(rs.getDate(SQL_EDUCATION_DATE_OF_ENTRY));
			education.setEducationTo(rs.getDate(SQL_EDUCATION_DATE_OF_GRADUATION));
			education.setEducationDescription(rs.getString(SQL_EDUCATION_DESCRIPTION));
			education.setKindEducation(rs.getString(SQL_EDUCATION_KIND));

			educationList.add(education);
		}

		return educationList;
	}

	@Override
	public boolean checkApplicantResume(int idResume) throws DAOException {
		ConnectionPool connectionPool = null;
		try {
			connectionPool = ConnectionPool.getInstance();
		} catch (ConnectionPoolException e) {
			log.fatal("Error connection pool instanse", e);
			throw new DAOException("Error connection pool instanse", e);
		}

		Connection connection = null;
		PreparedStatement checkResumePS = null;

		try {
			connection = connectionPool.takeConnection();
			
			checkResumePS = connection.prepareStatement(SQL_SELECT_RESUME_ID);
			checkResumePS.setInt(1, idResume);
			return checkResumePS.executeQuery().next();
		}catch (ConnectionPoolException | SQLException e) {
			log.error("Error checking resume of applicant", e);
			throw new DAOException("Error checking resume of applicant", e);
		} finally {
			
			try {
				checkResumePS.close();
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
