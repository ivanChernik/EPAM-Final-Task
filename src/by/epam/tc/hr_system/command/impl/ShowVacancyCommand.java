package by.epam.tc.hr_system.command.impl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import by.epam.tc.hr_system.command.ICommand;
import by.epam.tc.hr_system.domain.Vacancy;
import by.epam.tc.hr_system.exception.CommandException;
import by.epam.tc.hr_system.exception.ServiceException;
import by.epam.tc.hr_system.exception.validation.ValidationException;
import by.epam.tc.hr_system.service.IVacancyService;
import by.epam.tc.hr_system.service.ServiceFactory;
import by.epam.tc.hr_system.util.ErrorMessage;
import by.epam.tc.hr_system.util.PageName;
import by.epam.tc.hr_system.util.parameter.VacancyParameter;

/**
 * Command to show the vacancy by ID.
 * Screen with vacancy available for all roles of users
 * 
 * @author Ivan Chernikau
 *
 */
public class ShowVacancyCommand implements ICommand {

	private static final String ERROR_MESSAGES = "errormessages";
	private static final String VACANCY = "vacancy";
	private static final Logger log = Logger.getLogger(ShowVacancyCommand.class);

	/**
	 * Invoke IVacancyService to show the particular vacancy.
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
			} catch (ServiceException e) {
				throw new CommandException(e);
			} catch (ValidationException e) {
				request.setAttribute(ERROR_MESSAGES, ErrorMessage.ERROR_MESSAGE_VALIDATION_WAS_NOT_PASSED);
			}

			request.getRequestDispatcher(PageName.VACANCY_PAGE).forward(request, response);
		} catch (ServletException | IOException e) {
			log.error(e);
			throw new CommandException(e);
		}

	}
}
