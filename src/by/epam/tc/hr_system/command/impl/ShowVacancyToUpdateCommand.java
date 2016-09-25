package by.epam.tc.hr_system.command.impl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;

import by.epam.tc.hr_system.command.ICommand;
import by.epam.tc.hr_system.domain.Vacancy;
import by.epam.tc.hr_system.exception.CommandException;
import by.epam.tc.hr_system.exception.ServiceException;
import by.epam.tc.hr_system.exception.validation.IllegalEntriedValueException;
import by.epam.tc.hr_system.exception.validation.ValidationException;
import by.epam.tc.hr_system.service.IVacancyService;
import by.epam.tc.hr_system.service.ServiceFactory;
import by.epam.tc.hr_system.util.ErrorMessage;
import by.epam.tc.hr_system.util.PageName;
import by.epam.tc.hr_system.util.parameter.VacancyParameter;

/**
 * Command give all information of vacancy to update.
 * 
 * @author Ivan Chernikau
 *
 */
public class ShowVacancyToUpdateCommand implements ICommand {
	private static final String ERROR_MESSAGES = "errormessages";
	private static final String VACANCY = "vacancy";
	private static final Logger log = Logger.getLogger(ShowVacancyCommand.class);

	/**
	 * Invoke IVacancyService for giving information to update vacancy.
	 * 
	 * @param request
	 * @param response
	 * @throws CommandException
	 */

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
		try {

			String vacancyID = request.getParameter(VacancyParameter.ID);
			ServiceFactory serviceFactory = ServiceFactory.getInstance();

			try {
				IVacancyService vacancyService = serviceFactory.getVacancyService();
				Vacancy vacancy = vacancyService.getVacancyByID(vacancyID);
				request.setAttribute(VACANCY, vacancy);
			} catch (IllegalEntriedValueException e) {
				request.setAttribute(ERROR_MESSAGES, ErrorMessage.ERROR_MESSAGE_ILLEGAL_ENTRIED_VALUE);

			} catch (ValidationException e) {
				request.setAttribute(ERROR_MESSAGES, ErrorMessage.ERROR_MESSAGE_VALIDATION_WAS_NOT_PASSED);

			} catch (ServiceException e) {
				throw new CommandException(e);
			}

			request.getRequestDispatcher(PageName.UPDATE_VACANCY_PAGE).forward(request, response);
		} catch (ServletException | IOException e) {
			log.error(e);
			throw new CommandException(e);
		}

	}

}
