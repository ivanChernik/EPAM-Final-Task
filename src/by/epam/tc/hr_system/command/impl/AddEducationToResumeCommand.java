package by.epam.tc.hr_system.command.impl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import org.apache.log4j.Logger;

import by.epam.tc.hr_system.command.ICommand;
import by.epam.tc.hr_system.domain.ApplicantContactInfo;
import by.epam.tc.hr_system.domain.Education;
import by.epam.tc.hr_system.domain.Person;
import by.epam.tc.hr_system.domain.PreviousPosition;
import by.epam.tc.hr_system.domain.Resume;
import by.epam.tc.hr_system.exception.CommandException;
import by.epam.tc.hr_system.exception.ServiceException;
import by.epam.tc.hr_system.exception.validation.EmptyPropertyException;
import by.epam.tc.hr_system.exception.validation.IllegalDatesPeriodException;
import by.epam.tc.hr_system.exception.validation.IllegalSizeException;
import by.epam.tc.hr_system.exception.validation.InvalidFormatImageException;
import by.epam.tc.hr_system.exception.validation.PhotoNotChosenException;
import by.epam.tc.hr_system.exception.validation.ResumeDoesNotExistException;
import by.epam.tc.hr_system.exception.validation.ValidationException;
import by.epam.tc.hr_system.service.IResumeService;
import by.epam.tc.hr_system.service.ServiceFactory;
import by.epam.tc.hr_system.util.MessageManager;
import by.epam.tc.hr_system.util.PageName;
import by.epam.tc.hr_system.util.parameter.ResumeParamater;

public class AddEducationToResumeCommand implements ICommand {

	private static final String EDUCATION = "education";
	private static final String EDUCATION_TO = "educationTo";
	private static final String EDUCATION_FROM = "educationFrom";

	private static final String ERROR_MESSAGES = "errormessages";
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

			ServiceFactory serviceFactory = ServiceFactory.getInstance();

			try {
				IResumeService resumeService = serviceFactory.getResumeService();
				resumeService.addEducation(education, educationFrom, educationTo, person.getId());
				request.getRequestDispatcher("./resume.jsp?idResume=" + person.getId()).forward(request, response);
				return;
			} catch (ResumeDoesNotExistException e) {
				request.setAttribute(ERROR_MESSAGES, MessageManager.ERROR_MESSAGE_RESUME_DOES_NOT_EXIST);
			} catch (IllegalSizeException e) {
				request.setAttribute(ERROR_MESSAGES, MessageManager.ERROR_MESSAGE_ENTRY_VERY_LONG);

			} catch (IllegalDatesPeriodException e) {
				request.setAttribute(ERROR_MESSAGES, MessageManager.ERROR_MESSAGE_INVALID_DATE_VALUE);

			} catch (EmptyPropertyException e) {
				request.setAttribute(ERROR_MESSAGES, MessageManager.ERROR_MESSAGE_REQUERED_FILEDS_MISSED);
			} catch (ServiceException | ValidationException e) {
				throw new CommandException(e);
			}

			request.setAttribute(EDUCATION, education);
			request.setAttribute(EDUCATION_FROM, educationFrom);
			request.setAttribute(EDUCATION_TO, educationTo);

			request.getRequestDispatcher(PageName.ADDICTION_EDUCATION_PAGE).forward(request, response);

		} catch (ServletException | IOException e) {
			log.error(e);
			throw new CommandException(e);
		}
	}

}
