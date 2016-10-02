package by.epam.tc.hr_system.command.impl;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.log4j.Logger;

import by.epam.tc.hr_system.command.ICommand;
import by.epam.tc.hr_system.domain.Education;
import by.epam.tc.hr_system.domain.Person;
import by.epam.tc.hr_system.domain.PreviousPosition;
import by.epam.tc.hr_system.domain.Resume;
import by.epam.tc.hr_system.exception.CommandException;
import by.epam.tc.hr_system.exception.ServiceException;
import by.epam.tc.hr_system.exception.validation.EmptyPropertyException;
import by.epam.tc.hr_system.exception.validation.IllegalDatesPeriodException;
import by.epam.tc.hr_system.exception.validation.IllegalEntriedValueException;
import by.epam.tc.hr_system.exception.validation.IllegalSizeException;
import by.epam.tc.hr_system.exception.validation.InvalidFormatImageException;
import by.epam.tc.hr_system.exception.validation.PhotoNotChosenException;
import by.epam.tc.hr_system.exception.validation.ResumeAlreadyExistsException;
import by.epam.tc.hr_system.exception.validation.ValidationException;
import by.epam.tc.hr_system.service.IResumeService;
import by.epam.tc.hr_system.service.ServiceFactory;
import by.epam.tc.hr_system.domain.ApplicantContactInfo;
import by.epam.tc.hr_system.util.AuthorizationUser;
import by.epam.tc.hr_system.util.ErrorMessage;
import by.epam.tc.hr_system.util.PageName;
import by.epam.tc.hr_system.util.parameter.ResumeParamater;

/**
 * 
 * Command for creation resume. 
 * One applicant can have only one resume.
 * 
 * @author Ivan Chernikau
 *
 */

public class CreateResumeCommand implements ICommand {

	private static final String DO_NOT_WORK = "doNotWork";
	private static final String DO_NOT_STUDY = "doNotStudy";
	private static final String WORK_TO = "workTo";
	private static final String WORK_FROM = "workFrom";
	private static final String EDUCATION_TO = "educationTo";
	private static final String EDUCATION_FROM = "educationFrom";
	private static final String RESUME = "resume";
	private static final String IMAGE_MIME_TYPE = "image/";

	private static final String ERROR_MESSAGES = "errormessages";
	private static final String FILE = "photo";

	private static final Logger log = Logger.getLogger(CreateResumeCommand.class);

	/**
	 * Invoke IResumeService for checking existing resume of appropriate applicant.
	 * Steps:
	 * 1. Checking existing.
	 * 2. Addiction resume.
	 * @param request
	 * @param response
	 * @throws CommandException
	 */
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
		try {
			Person person = AuthorizationUser.getPersonInSession(request);
			if (person == null) {
				request.getRequestDispatcher(PageName.INDEX_PAGE).forward(request, response);
				return;
			}

			String position = request.getParameter(ResumeParamater.POSITION);
			String profInformation = request.getParameter(ResumeParamater.PROF_INFIRMATION);
			String skill = request.getParameter(ResumeParamater.SKILL);

			Resume resume = new Resume(position, profInformation, skill);

			String kindEducation = request.getParameter(ResumeParamater.KIND_EDUCATION);
			String university = request.getParameter(ResumeParamater.UNIVERSITY);
			String faculty = request.getParameter(ResumeParamater.FACULTY);
			String specialty = request.getParameter(ResumeParamater.SPECIALTY);
			String formEducation = request.getParameter(ResumeParamater.FORM_EDUCATION);
			String educationFrom = request.getParameter(ResumeParamater.EDUCATION_FROM);
			String educationTo = request.getParameter(ResumeParamater.EDUCATION_TO);
			String description = request.getParameter(ResumeParamater.EDUCATION_DESCRIPTION);

			Education education = new Education(kindEducation, university, faculty, specialty, formEducation,
					description);
			resume.addEducation(education);

			String prevpositionName = request.getParameter(ResumeParamater.PREVIOS_POSITION);
			String workFrom = request.getParameter(ResumeParamater.WORK_FROM);
			String workTo = request.getParameter(ResumeParamater.WORK_TO);
			String workDescription = request.getParameter(ResumeParamater.WORK_DESCRIPTION);

			PreviousPosition prevPosition = new PreviousPosition(prevpositionName, workDescription);
			resume.addPreviousWork(prevPosition);

			ApplicantContactInfo contactInfo = resume.getContactInfo();

			contactInfo.setPhone(request.getParameter(ResumeParamater.PHONE));
			contactInfo.setEmail(request.getParameter(ResumeParamater.EMAIL));
			contactInfo.setAddress(request.getParameter(ResumeParamater.ADDRESS));

			contactInfo.setLinkGooglePlus(request.getParameter(ResumeParamater.LINK_GOOGLE_PLUS));
			contactInfo.setLinkLinkedIn(request.getParameter(ResumeParamater.LINK_LINKEDIN));
			contactInfo.setLinkTwitter(request.getParameter(ResumeParamater.LINK_TWITTER));
			contactInfo.setLinkFacebook(request.getParameter(ResumeParamater.LINK_FACEBOOK));

			String doNotStudy = request.getParameter(DO_NOT_STUDY);
			String doNotWork = request.getParameter(DO_NOT_WORK);

			Part filePart = request.getPart(FILE);
			String filename = filePart.getSubmittedFileName();
			String mimeType = request.getServletContext().getMimeType(filename);
			String realpath = request.getServletContext().getRealPath("");

			ServiceFactory serviceFactory = ServiceFactory.getInstance();

			try {
				validateAndLoadImage(filePart, filename, mimeType, realpath);
				IResumeService resumeService = serviceFactory.getResumeService();
				resumeService.checkExistingResume(person.getId());
				resumeService.addResume(resume, person.getId(), educationFrom, educationTo, workFrom, workTo, filename,
						doNotStudy, doNotWork);
				request.getRequestDispatcher(PageName.INDEX_APPLICANT_PAGE).forward(request, response);
				return;
			} catch (PhotoNotChosenException e) {
				request.setAttribute(ERROR_MESSAGES, ErrorMessage.ERROR_MESSAGE_PHOTO_NOT_UPLOADED);

			} catch (ResumeAlreadyExistsException e) {
				request.setAttribute(ERROR_MESSAGES, ErrorMessage.ERROR_MESSAGE_RESUME_ALREADY_EXISTS);

			} catch (InvalidFormatImageException e) {
				request.setAttribute(ERROR_MESSAGES, ErrorMessage.ERROR_MESSAGE_PHOTO_HAS_WRONG_FORMAT);

			} catch (IllegalSizeException e) {
				request.setAttribute(ERROR_MESSAGES, ErrorMessage.ERROR_MESSAGE_ENTRY_VERY_LONG);

			} catch (IllegalDatesPeriodException e) {
				request.setAttribute(ERROR_MESSAGES, ErrorMessage.ERROR_MESSAGE_INVALID_DATE_VALUE);

			} catch (EmptyPropertyException e) {
				request.setAttribute(ERROR_MESSAGES, ErrorMessage.ERROR_MESSAGE_REQUERED_FILEDS_MISSED);

			} catch (IllegalEntriedValueException e) {
				request.setAttribute(ERROR_MESSAGES, ErrorMessage.ERROR_MESSAGE_ILLEGAL_ENTRIED_VALUE);
				
			} catch (ValidationException e) {
				request.setAttribute(ERROR_MESSAGES, ErrorMessage.ERROR_MESSAGE_VALIDATION_WAS_NOT_PASSED);
				
			} catch (ServiceException e) {
				throw new CommandException(e);
			}

			request.setAttribute(RESUME, resume);
			request.setAttribute(EDUCATION_FROM, educationFrom);
			request.setAttribute(EDUCATION_TO, educationTo);
			request.setAttribute(WORK_FROM, workFrom);
			request.setAttribute(WORK_TO, workTo);

			request.getRequestDispatcher(PageName.CREATE_RESUME_PAGE).forward(request, response);

		} catch (ServletException | IOException e) {
			log.error(e);
			throw new CommandException(e);
		}
	}

	private String validateAndLoadImage(Part filePart, String filename, String mimeType, String realpath)
			throws CommandException {
		if (!filename.isEmpty()) {

			if (mimeType.startsWith(IMAGE_MIME_TYPE)) {
				File uploads = new File(realpath);
				File file = new File(uploads, filename);
				try (InputStream input = filePart.getInputStream()) {
					Files.copy(input, file.toPath(), StandardCopyOption.REPLACE_EXISTING);
				} catch (IOException e) {
					log.error("Error reading user photo", e);
					throw new CommandException(e);
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

}
