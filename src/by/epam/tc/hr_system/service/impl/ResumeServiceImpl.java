package by.epam.tc.hr_system.service.impl;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.sql.Date;

import javax.servlet.http.Part;

import org.apache.log4j.Logger;

import com.sun.media.sound.InvalidFormatException;

import by.epam.tc.hr_system.dao.DAOFactory;
import by.epam.tc.hr_system.dao.IResumeDAO;
import by.epam.tc.hr_system.dao.IVacancyDAO;
import by.epam.tc.hr_system.domain.ApplicantContactInfo;
import by.epam.tc.hr_system.domain.Education;
import by.epam.tc.hr_system.domain.PreviousPosition;
import by.epam.tc.hr_system.domain.Resume;
import by.epam.tc.hr_system.exception.DAOException;
import by.epam.tc.hr_system.exception.ServiceException;
import by.epam.tc.hr_system.exception.validation.InvalidFormatImageException;
import by.epam.tc.hr_system.exception.validation.PhotoNotChosenException;
import by.epam.tc.hr_system.service.IResumeService;
import by.epam.tc.hr_system.util.validation.StringConverter;
import static by.epam.tc.hr_system.util.validation.Validator.*;

public class ResumeServiceImpl implements IResumeService {

	private static final String IMAGE_MIME_TYPE = "image/";

	private static final Logger log = Logger.getLogger(ResumeServiceImpl.class);

	@Override
	public void addResume(Resume resume, int userId, String educationFrom, String educationTo, String workFrom,
			String workTo, Part filePart, String filename, String mimeType, String realpath) throws ServiceException {

		validatePositiveInt(userId);
		filename = validateAndLoadImage(filePart, realpath, realpath, realpath);

		resume.setSkill(validateRequiredString(resume.getSkill(), 300));
		resume.setPosition(validateRequiredString(resume.getSkill(), 30));
		resume.setProfInformation(validateRequiredString(resume.getProfInformation(), 800));

		resume.setContactInfo(validateContactInfo(resume.getContactInfo()));
		resume.addPreviousWork(validatePreviousPosition(resume.getPreviousWorkList().get(0), workFrom, workTo));
		resume.addEducation(validateEducation(resume.getEducationList().get(0), educationFrom, educationTo));

		DAOFactory daoFactory = DAOFactory.getInstance();

		try {
			IResumeDAO resumeDAO = daoFactory.getResumeDAO();
			resumeDAO.addResume(resume, userId);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}

	}

	private String validateAndLoadImage(Part filePart, String filename, String mimeType, String realpath)
			throws ServiceException {
		if (!filename.isEmpty()) {

			if (mimeType.startsWith(IMAGE_MIME_TYPE)) {
				File uploads = new File(realpath);
				File file = new File(uploads, filename);
				try (InputStream input = filePart.getInputStream()) {
					Files.copy(input, file.toPath(), StandardCopyOption.REPLACE_EXISTING);
				} catch (IOException e) {
					log.error("Error reading user photo", e);
					throw new ServiceException(e);
				}
			} else {
				log.warn("Warning: format photo");
				throw new InvalidFormatImageException("Error format photo");
			}
		} else {
			log.warn("Warning: photo was not picked");
			throw new PhotoNotChosenException("Warning: photo was not picked");
		}
		return filename;
	}

	private PreviousPosition validatePreviousPosition(PreviousPosition prevPosition, String workFrom, String workTo) {
		prevPosition.setPreviousPosition(validateRequiredString(prevPosition.getPreviousPosition(), 30));
		prevPosition.setWorkDescription(validateRequiredString(prevPosition.getWorkDescription(), 1000));

		Date workFromDate = StringConverter.parseStringToDate(workFrom);
		Date workToDate = StringConverter.parseStringToDate(workTo);

		validateDatesPeriod(workFromDate, workToDate);

		prevPosition.setWorkFrom(workFromDate);
		prevPosition.setWorkTo(workToDate);
		return prevPosition;
	}

	private Education validateEducation(Education education, String educationFrom, String educationTo) {

		education.setEducationDescription(validateRequiredString(education.getEducationDescription(), 1000));
		education.setUniversity(validateRequiredString(education.getUniversity(), 100));
		education.setFaculty(validateRequiredString(education.getFaculty(), 100));
		education.setSpecialty(validateRequiredString(education.getSpecialty(), 100));

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
	public void addEducation(Education education, String educationFrom, String educationTo,int idUser) throws ServiceException {
		validatePositiveInt(idUser);
		education = validateEducation(education, educationFrom, educationTo);
		
		DAOFactory daoFactory = DAOFactory.getInstance();
		
		try {
			IResumeDAO resumeDAO = daoFactory.getResumeDAO();
			if (resumeDAO.checkApplicantResume(idUser)) {
				resumeDAO.addEducation(education, idUser);;
			}
		} catch (DAOException e) {
			throw new ServiceException(e);
		}

	}

	@Override
	public void addPreviousPosition(PreviousPosition prevPosition, String workFrom, String workTo ,int idUser)
			throws ServiceException {
		validatePositiveInt(idUser);
		prevPosition = validatePreviousPosition(prevPosition, workFrom, workTo);
		
	DAOFactory daoFactory = DAOFactory.getInstance();
		
		try {
			IResumeDAO resumeDAO = daoFactory.getResumeDAO();
			if (resumeDAO.checkApplicantResume(idUser)) {
				resumeDAO.addPreviousPosition(prevPosition, idUser);
			}
		} catch (DAOException e) {
			throw new ServiceException(e);
		}

	}

}
