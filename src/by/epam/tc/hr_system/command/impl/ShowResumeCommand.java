package by.epam.tc.hr_system.command.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epam.tc.hr_system.command.ICommand;
import by.epam.tc.hr_system.domain.Resume;
import by.epam.tc.hr_system.exception.CommandException;
import by.epam.tc.hr_system.exception.ServiceException;
import by.epam.tc.hr_system.exception.validation.IllegalEntriedValueException;
import by.epam.tc.hr_system.exception.validation.ValidationException;
import by.epam.tc.hr_system.service.IResumeService;
import by.epam.tc.hr_system.service.ServiceFactory;
import by.epam.tc.hr_system.util.ErrorMessage;
import by.epam.tc.hr_system.util.parameter.ResumeParamater;

/**
 * Command show the resume.Screen with resume is available for all roles of
 * users.
 * 
 * @author Ivan Chernikau
 *
 */

public class ShowResumeCommand implements ICommand {

	private static final String ERROR_MESSAGES = "errormessages";
	private static final String RESUME = "resume";

	/**
	 * Invoke IResumeService to show the resume by ID
	 * 
	 * @param request
	 * @param response
	 * @throws CommandException
	 */
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
		String idResumeString = request.getParameter(ResumeParamater.ID_RESUME);

		ServiceFactory serviceFactory = ServiceFactory.getInstance();

		try {
			IResumeService responceService = serviceFactory.getResumeService();
			Resume resume = responceService.getApplicantResume(idResumeString);
			request.setAttribute(RESUME, resume);
		} catch (IllegalEntriedValueException e) {
			request.setAttribute(ERROR_MESSAGES, ErrorMessage.ERROR_MESSAGE_ILLEGAL_ENTRIED_VALUE);
		} catch (ValidationException e) {
			request.setAttribute(ERROR_MESSAGES, ErrorMessage.ERROR_MESSAGE_VALIDATION_WAS_NOT_PASSED);
		} catch (ServiceException e) {
			throw new CommandException(e);
		}
	}

}
