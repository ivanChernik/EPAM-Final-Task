package by.epam.tc.hr_system.command.impl;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.sql.Date;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import javax.xml.bind.ValidationException;

import org.apache.log4j.Logger;

import by.epam.tc.hr_system.command.ICommand;
import by.epam.tc.hr_system.domain.Education;
import by.epam.tc.hr_system.domain.Person;
import by.epam.tc.hr_system.domain.PreviousPosition;
import by.epam.tc.hr_system.domain.Resume;
import by.epam.tc.hr_system.exception.CommandException;
import by.epam.tc.hr_system.exception.ServiceException;
import by.epam.tc.hr_system.exception.validation.InvalidFormatImageException;
import by.epam.tc.hr_system.exception.validation.PhotoNotChosenException;
import by.epam.tc.hr_system.exception.validation.ValidationExeception;
import by.epam.tc.hr_system.service.IResumeService;
import by.epam.tc.hr_system.service.ServiceFactory;
import by.epam.tc.hr_system.domain.ApplicantContactInfo;
import by.epam.tc.hr_system.util.MessageManager;
import by.epam.tc.hr_system.util.PageName;
import by.epam.tc.hr_system.util.parameter.ResumeParamater;

public class CreateResumeCommand implements ICommand {

	private static final String WORK_TO = "workTo";
	private static final String WORK_FROM = "workFrom";
	private static final String EDUCATION_TO = "educationTo";
	private static final String EDUCATION_FROM = "educationFrom";
	private static final String RESUME = "resume";

	private static final String ERRORMESSAGES = "errormessages";
	private static final String FILE = "photo";
	private static final String PERSON = "person";

	private static final Logger log = Logger.getLogger(CreateResumeCommand.class);

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
		try {
			HttpSession session = request.getSession(true);

			Person person = (Person) session.getAttribute(PERSON);

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

			Education education = new Education(university, faculty, specialty, description);
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

			Part filePart = request.getPart(FILE);
			String filename = filePart.getSubmittedFileName();
			String mimeType = request.getServletContext().getMimeType(filename);
			String realpath = request.getServletContext().getRealPath("");

			ServiceFactory serviceFactory = ServiceFactory.getInstance();

			try {
				IResumeService resumeService = serviceFactory.getResumeService();
				resumeService.addResume(resume, person.getId(), educationFrom, educationTo, workFrom, workTo, filePart,
						filename, mimeType, realpath);
				request.getRequestDispatcher(PageName.INDEX_APPLICANT_PAGE).forward(request, response);
				return;
			} catch (ServiceException e) {
				request.getRequestDispatcher(PageName.CREATE_RESUME_PAGE).forward(request, response);
				return;
			} catch (ValidationExeception e) {
				request.setAttribute(ERRORMESSAGES, MessageManager.ERROR_MESSAGE_REQUERED_FILEDS_MISSED);
			} catch (PhotoNotChosenException e) {
				request.setAttribute(ERRORMESSAGES, MessageManager.ERROR_MESSAGE_PHOTO_NOT_UPLOADED);

			} catch (InvalidFormatImageException e) {
				request.setAttribute(ERRORMESSAGES, MessageManager.ERROR_MESSAGE_PHOTO_HAS_WRONG_FORMAT);
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

}
