package by.epam.tc.hr_system.service.impl;

import java.sql.Date;
import java.util.List;

import by.epam.tc.hr_system.dao.DAOFactory;
import by.epam.tc.hr_system.dao.IResumeDAO;
import by.epam.tc.hr_system.domain.ApplicantContactInfo;
import by.epam.tc.hr_system.domain.Education;
import by.epam.tc.hr_system.domain.PreviousPosition;
import by.epam.tc.hr_system.domain.Resume;
import by.epam.tc.hr_system.exception.DAOException;
import by.epam.tc.hr_system.exception.ServiceException;
import by.epam.tc.hr_system.exception.validation.ResumeAlreadyExistsException;
import by.epam.tc.hr_system.exception.validation.ResumeDoesNotExistException;
import by.epam.tc.hr_system.service.IResumeService;
import by.epam.tc.hr_system.util.validation.StringConverter;
import static by.epam.tc.hr_system.util.validation.Validator.*;

/**
 * Service implementation for resume.
 * 
 * @author Ivan Chernikau
 *
 */
public class ResumeServiceImpl implements IResumeService {

	@Override
	public void addResume(Resume resume, int userId, String educationFrom, String educationTo, String workFrom,
			String workTo, String filename, String doNotStudy, String doNotWork) throws ServiceException {

		validatePositiveInt(userId);
		validateEmptyString(filename);
		resume.setPathImage(filename);
		resume.setSkill(validateRequiredString(resume.getSkill(), 300));
		resume.setPosition(validateRequiredString(resume.getPosition(), 30));
		resume.setProfInformation(validateRequiredString(resume.getProfInformation(), 800));

		resume.setContactInfo(validateContactInfo(resume.getContactInfo()));

		if (doNotWork == null) {
			resume.addPreviousWork(validatePreviousPosition(resume.getPreviousWorkList().get(0), workFrom, workTo));
		}

		if (doNotStudy == null) {
			resume.addEducation(validateEducation(resume.getEducationList().get(0), educationFrom, educationTo));
		}

		DAOFactory daoFactory = DAOFactory.getInstance();

		try {
			IResumeDAO resumeDAO = daoFactory.getResumeDAO();
			resumeDAO.addResume(resume, userId, doNotStudy, doNotWork);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}

	}

	private PreviousPosition validatePreviousPosition(PreviousPosition prevPosition, String workFrom, String workTo) {
		prevPosition.setPreviousPosition(validateNotRequiredString(prevPosition.getPreviousPosition(), 30));
		prevPosition.setWorkDescription(validateNotRequiredString(prevPosition.getWorkDescription(), 1000));

		Date workFromDate = StringConverter.parseStringToDate(workFrom);
		Date workToDate = StringConverter.parseStringToDate(workTo);

		validateDatesPeriod(workFromDate, workToDate);

		prevPosition.setWorkFrom(workFromDate);
		prevPosition.setWorkTo(workToDate);
		return prevPosition;
	}

	private Education validateEducation(Education education, String educationFrom, String educationTo) {

		education.setEducationDescription(validateNotRequiredString(education.getEducationDescription(), 1000));
		education.setUniversity(validateNotRequiredString(education.getUniversity(), 100));
		education.setFaculty(validateNotRequiredString(education.getFaculty(), 100));
		education.setSpecialty(validateNotRequiredString(education.getSpecialty(), 100));

		Date educationFromDate = StringConverter.parseStringToDate(educationFrom);
		Date educationToDate = StringConverter.parseStringToDate(educationTo);

		validateDatesPeriod(educationFromDate, educationToDate);

		education.setEducationFrom(educationFromDate);
		education.setEducationTo(educationToDate);

		validateSelectedItem(Education.getFormEducationList(), education.getFormEducation());
		validateSelectedItem(Education.getKindEducationList(), education.getKindEducation());
		return education;
	}

	private ApplicantContactInfo validateContactInfo(ApplicantContactInfo contactInfo) {

		contactInfo.setAddress(validateRequiredString(contactInfo.getAddress(), 45));
		contactInfo.setEmail(validateRequiredString(contactInfo.getEmail(), 45));
		contactInfo.setPhone(validateRequiredString(contactInfo.getPhone(), 45));
		contactInfo.setLinkFacebook(validateNotRequiredString(contactInfo.getLinkFacebook(), 45));
		contactInfo.setLinkGooglePlus(validateNotRequiredString(contactInfo.getLinkGooglePlus(), 45));
		contactInfo.setLinkLinkedIn(validateNotRequiredString(contactInfo.getLinkLinkedIn(), 45));
		contactInfo.setLinkTwitter(validateNotRequiredString(contactInfo.getLinkTwitter(), 45));
		return contactInfo;
	}

	@Override
	public int getCountResumes() throws ServiceException {
		DAOFactory daoFactory = DAOFactory.getInstance();
		int countResumes = 0;
		try {
			IResumeDAO resumeDAO = daoFactory.getResumeDAO();
			countResumes = resumeDAO.getCountResumes();
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		return countResumes;
	}

	@Override
	public Resume getApplicantResume(String idResumeString) throws ServiceException {

		int idResume = StringConverter.parseStringToInt(idResumeString);

		DAOFactory daoFactory = DAOFactory.getInstance();
		Resume resume = null;
		try {
			IResumeDAO resumeDAO = daoFactory.getResumeDAO();
			if (resumeDAO.checkApplicantResume(idResume)) {
				resume = resumeDAO.getApplicantResume(idResume);
			}
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		return resume;
	}

	@Override
	public void addEducation(Education education, String educationFrom, String educationTo, int idUser)
			throws ServiceException {
		validatePositiveInt(idUser);
		education = validateEducation(education, educationFrom, educationTo);

		DAOFactory daoFactory = DAOFactory.getInstance();

		try {
			IResumeDAO resumeDAO = daoFactory.getResumeDAO();
			if (resumeDAO.checkApplicantResume(idUser)) {
				resumeDAO.addEducation(education, idUser);
			} else {
				throw new ResumeDoesNotExistException("Error addiction education");
			}
		} catch (DAOException e) {
			throw new ServiceException(e);
		}

	}

	@Override
	public void addPreviousPosition(PreviousPosition prevPosition, String workFrom, String workTo, int idUser)
			throws ServiceException {
		validatePositiveInt(idUser);
		prevPosition = validatePreviousPosition(prevPosition, workFrom, workTo);

		DAOFactory daoFactory = DAOFactory.getInstance();

		try {
			IResumeDAO resumeDAO = daoFactory.getResumeDAO();
			if (resumeDAO.checkApplicantResume(idUser)) {
				resumeDAO.addPreviousPosition(prevPosition, idUser);
			} else {
				throw new ResumeDoesNotExistException("Error addiction prev position");
			}
		} catch (DAOException e) {
			throw new ServiceException(e);
		}

	}

	@Override
	public void checkExistingResume(int idApplicant) throws ServiceException {
		validatePositiveInt(idApplicant);

		DAOFactory daoFactory = DAOFactory.getInstance();

		try {
			IResumeDAO resumeDAO = daoFactory.getResumeDAO();
			if (resumeDAO.checkApplicantResume(idApplicant)) {
				throw new ResumeAlreadyExistsException("Error: resume is existing");
			}
		} catch (DAOException e) {
			throw new ServiceException(e);
		}

	}

	@Override
	public List<Resume> searchResumeByParameter(String position, String kindEducation) throws ServiceException {

		validateRequiredString(position, 30);
		validateSelectedItem(Education.getKindEducationList(), kindEducation);

		List<Resume> resumeList = null;
		DAOFactory daoFactory = DAOFactory.getInstance();

		try {
			IResumeDAO resumeDAO = daoFactory.getResumeDAO();
			resumeList = resumeDAO.searchResumeByParameters(position, kindEducation);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		return resumeList;
	}

	@Override
	public void deleteResume(int idApplicant) throws ServiceException {
		validatePositiveInt(idApplicant);

		DAOFactory daoFactory = DAOFactory.getInstance();

		try {
			IResumeDAO resumeDAO = daoFactory.getResumeDAO();
			resumeDAO.deleteResume(idApplicant);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

}
