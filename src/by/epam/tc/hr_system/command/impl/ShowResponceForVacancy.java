package by.epam.tc.hr_system.command.impl;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import by.epam.tc.hr_system.command.ICommand;
import by.epam.tc.hr_system.domain.Person;
import by.epam.tc.hr_system.domain.VacancyResponse;
import by.epam.tc.hr_system.exception.CommandException;
import by.epam.tc.hr_system.exception.ServiceException;
import by.epam.tc.hr_system.exception.validation.IllegalEntriedValueException;
import by.epam.tc.hr_system.exception.validation.ValidationException;
import by.epam.tc.hr_system.service.IVacancyResponseService;
import by.epam.tc.hr_system.service.ServiceFactory;
import by.epam.tc.hr_system.util.ErrorMessage;
import by.epam.tc.hr_system.util.PageName;
import by.epam.tc.hr_system.util.validation.AuthorizingUser;

/**
 * Command shows all responses (link to resumes and etc) for particular vacancy
 * 
 * @author Ivan Chernikau
 *
 */
public class ShowResponceForVacancy implements ICommand {

	private static final String ERROR_MESSAGES = "errormessages";
	private static final String RESPONCE_LIST = "responceList";
	private static final String ID_VACANCY = "idVacancy";

	private static final Logger log = Logger.getLogger(ShowResponceForVacancy.class);

	/**
	 * Invoke IVacancyResponceService to show all responses (link to resumes and
	 * etc) for particular vacancy
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

			String idVacancyString = request.getParameter(ID_VACANCY);

			ServiceFactory serviceFactory = ServiceFactory.getInstance();

			List<VacancyResponse> responceList = null;
			try {
				IVacancyResponseService responceService = serviceFactory.getVacancyResponceService();
				responceList = responceService.getReponcesForVacancy(idVacancyString);
			} catch (IllegalEntriedValueException e) {
				request.setAttribute(ERROR_MESSAGES, ErrorMessage.ERROR_MESSAGE_ILLEGAL_ENTRIED_VALUE);
			} catch (ValidationException e) {
				request.setAttribute(ERROR_MESSAGES, ErrorMessage.ERROR_MESSAGE_VALIDATION_WAS_NOT_PASSED);
			} catch (ServiceException e) {
				throw new CommandException(e);
			}

			request.setAttribute(RESPONCE_LIST, responceList);
			request.getRequestDispatcher(PageName.VACANCY_RESPONCE_PAGE).forward(request, response);

		} catch (ServletException | IOException e) {
			log.error(e);
			throw new CommandException(e);
		}

	}

}
