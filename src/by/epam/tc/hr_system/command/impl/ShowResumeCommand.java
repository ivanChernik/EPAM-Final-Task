package by.epam.tc.hr_system.command.impl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import by.epam.tc.hr_system.command.ICommand;
import by.epam.tc.hr_system.domain.Person;
import by.epam.tc.hr_system.domain.Resume;
import by.epam.tc.hr_system.exception.CommandException;
import by.epam.tc.hr_system.exception.ServiceException;
import by.epam.tc.hr_system.exception.validation.ValidationException;
import by.epam.tc.hr_system.service.IResumeService;
import by.epam.tc.hr_system.service.ServiceFactory;
import by.epam.tc.hr_system.util.MessageManager;
import by.epam.tc.hr_system.util.PageName;
import by.epam.tc.hr_system.util.parameter.ResumeParamater;
import by.epam.tc.hr_system.util.parameter.UserParameter;

public class ShowResumeCommand implements ICommand {

	private static final String ERROR_MESSAGES = "errormessages";
	private static final String PERSON = "person";
	private static final String RESUME = "resume";
	private static final Logger log = Logger.getLogger(ShowResumeCommand.class);

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
		try {
			HttpSession session = request.getSession(false);
			Person person = (Person) session.getAttribute(PERSON);

			if (person == null) {
				request.getRequestDispatcher(PageName.INDEX_PAGE).forward(request, response);
				return;
			}
			
			String idResumeString = request.getParameter(ResumeParamater.ID_RESUME);

			ServiceFactory serviceFactory = ServiceFactory.getInstance();

			try {
				IResumeService responceService = serviceFactory.getResumeService();
				Resume resume = responceService.getApplicantResume(idResumeString);
				request.setAttribute(RESUME, resume);
			} catch (ServiceException e) {
				throw new CommandException(e);
			} catch (ValidationException e) {
				request.setAttribute(ERROR_MESSAGES, MessageManager.ERROR_MESSAGE_IMPOSSIBLE_ACTION);
			}


		} catch (ServletException | IOException e) {
			log.error(e);
			throw new CommandException(e);
		}
	}

}
