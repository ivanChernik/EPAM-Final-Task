package by.epam.tc.hr_system.command.impl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import by.epam.tc.hr_system.command.ICommand;
import by.epam.tc.hr_system.domain.Person;
import by.epam.tc.hr_system.exception.CommandException;
import by.epam.tc.hr_system.exception.ServiceException;
import by.epam.tc.hr_system.exception.validation.ValidationException;
import by.epam.tc.hr_system.service.IVacancyService;
import by.epam.tc.hr_system.service.ServiceFactory;
import by.epam.tc.hr_system.util.ErrorMessage;
import by.epam.tc.hr_system.util.PageName;
import by.epam.tc.hr_system.util.parameter.VacancyParameter;
import by.epam.tc.hr_system.util.validation.AuthorizingUser;

/**
 * 
 * Command for checking affiliation vacancy to appropriate HR.
 * 
 * @author Ivan Chernikau
 *
 */
public class CheckAffiliationVacancyToHRCommand implements ICommand {

	private static final String BELONGS = "belongs";
	private static final String ERROR_MESSAGES = "errormessages";
	private static final Logger log = Logger.getLogger(ShowResumeCommand.class);

	/**
	 * Invoke IVacancyService for checking affiliation vacancy to appropriate HR.
	 * 
	 * @param request
	 * @param response
	 * @throws CommandException
	 */
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
		try {
			Person person = AuthorizingUser.getPersonInSession(request);

			if (person == null) {
				request.getRequestDispatcher(PageName.INDEX_PAGE).forward(request, response);
				return;
			}
			
			String idVacancyString = request.getParameter(VacancyParameter.ID);

			ServiceFactory serviceFactory = ServiceFactory.getInstance();

			try {
				IVacancyService vacancyService = serviceFactory.getVacancyService();
				boolean result = vacancyService.checkVacancyToHR(person.getId(), idVacancyString);
				request.setAttribute(BELONGS, result);
			} catch (ServiceException e) {
				throw new CommandException(e);
			} catch (ValidationException e) {
				request.setAttribute(ERROR_MESSAGES, ErrorMessage.ERROR_MESSAGE_VALIDATION_WAS_NOT_PASSED);
			}


		} catch (ServletException | IOException e) {
			log.error(e);
			throw new CommandException(e);
		}
	}

}
