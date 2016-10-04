package by.epam.tc.hr_system.command.impl;

import java.io.IOException;

import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import by.epam.tc.hr_system.command.ICommand;
import by.epam.tc.hr_system.domain.Person;
import by.epam.tc.hr_system.domain.VacancyResponse;
import by.epam.tc.hr_system.exception.CommandException;
import by.epam.tc.hr_system.exception.ServiceException;
import by.epam.tc.hr_system.exception.validation.ValidationException;
import by.epam.tc.hr_system.service.IVacancyResponseService;
import by.epam.tc.hr_system.service.ServiceFactory;
import by.epam.tc.hr_system.util.AuthorizationUser;
import by.epam.tc.hr_system.util.ErrorMessage;
import by.epam.tc.hr_system.util.PageName;

/**
 * Command for showing all vacancies, which user sent the resume for.
 * 
 * @author Ivan Chernikau
 *
 */
public class ShowApplicantResponceCommand implements ICommand {

	private static final String ERROR_MESSAGES = "errormessages";
	private static final String RESPONCE_LIST = "responceList";
	private static final Logger log = Logger.getLogger(ShowApplicantResponceCommand.class);

	
	/**
	 * Invoke IVacancyResponceService to show all vacancies, which user applied for.
	 * 
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

			ServiceFactory serviceFactory = ServiceFactory.getInstance();

			List<VacancyResponse> responceList = null;
			try {
				IVacancyResponseService responceService = serviceFactory.getVacancyResponceService();
				responceList = responceService.getApplicantReponces(person.getId());
				request.setAttribute(RESPONCE_LIST, responceList);
			} catch (ValidationException e) {
				request.setAttribute(ERROR_MESSAGES, ErrorMessage.ERROR_MESSAGE_VALIDATION_WAS_NOT_PASSED);
			} catch (ServiceException e) {
				throw new CommandException(e);
			}

		} catch (ServletException | IOException e) {
			log.error(e);
			throw new CommandException(e);
		}
	}

}
