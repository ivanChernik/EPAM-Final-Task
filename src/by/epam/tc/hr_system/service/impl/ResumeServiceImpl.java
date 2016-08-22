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
import by.epam.tc.hr_system.domain.Education;
import by.epam.tc.hr_system.domain.PreviousPosition;
import by.epam.tc.hr_system.domain.Resume;
import by.epam.tc.hr_system.exception.DAOException;
import by.epam.tc.hr_system.exception.ServiceException;
import by.epam.tc.hr_system.exception.validation.InvalidFormatImageException;
import by.epam.tc.hr_system.exception.validation.PhotoNotChosenException;
import by.epam.tc.hr_system.service.IResumeService;
import by.epam.tc.hr_system.util.validation.Validator;

public class ResumeServiceImpl implements IResumeService {

	private static final String IMAGE_MIME_TYPE = "image/";

	private static final Logger log = Logger.getLogger(ResumeServiceImpl.class);

	@Override
	public void addResume(Resume resume, int userId, String educationFrom, String educationTo, String workFrom,
			String workTo, Part filePart, String filename, String mimeType, String realpath) throws ServiceException {

		filename = validateAndLoadImage(filePart, realpath, realpath, realpath);

		PreviousPosition prevPosition = resume.getPreviousWorkList().get(0);

		prevPosition.setPreviousPosition(Validator.validateInputString(prevPosition.getPreviousPosition()));
		prevPosition.setWorkDescription(Validator.validateTextAreaString(prevPosition.getWorkDescription()));

		Date workFromDate = Validator.parseStringToDate(workFrom);
		Date workToDate = Validator.parseStringToDate(workTo);

		Validator.validateDatesPeriod(workFromDate, workToDate);

		prevPosition.setWorkFrom(workFromDate);
		prevPosition.setWorkTo(workToDate);

		Education education = resume.getEducationList().get(0);

		education.setEducationDescription(Validator.validateTextAreaString(education.getEducationDescription()));
		education.setUniversity(Validator.validateInputString(education.getUniversity()));
		education.setFaculty(Validator.validateInputString(education.getFaculty()));
		education.setSpecialty(Validator.validateInputString(education.getSpecialty()));

		Date educationFromDate = Validator.parseStringToDate(educationFrom);
		Date educationToDate = Validator.parseStringToDate(educationTo);

		Validator.validateDatesPeriod(educationFromDate, educationToDate);

		education.setEducationFrom(educationFromDate);
		education.setEducationTo(educationToDate);

		education.setFormEducation(Validator.validateInputString(education.getFormEducation()));
		education.setKindEducation(Validator.validateInputString(education.getKindEducation()));

		// other information

		resume.setSkill(Validator.validateInputString(resume.getSkill()));
		resume.setPosition(Validator.validateInputString(resume.getSkill()));
		resume.setProfInformation(Validator.validateTextAreaString(resume.getProfInformation()));

		Validator.validateInt(userId);

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

		int idResume = Validator.parseStringToInt(idResumeString);

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

}
