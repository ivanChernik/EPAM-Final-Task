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
import by.epam.tc.hr_system.domain.Vacancy;
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
 * Command for removal one or more vacancies of appropriate HR-employee
 * 
 * @author Ivan Chernikau
 *
 */
public class DeleteVacancyCommand implements ICommand {
	private static final String VACANCY_LIST = "vacancyList";
	private static final String ERROR_MESSAGES = "errorMessage";

	private static final Logger log = Logger.getLogger(CreateVacancyCommand.class);


	/**
	 * Invoke IVacancyService for removal vacancy(s). 
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

			String[] idResponceArrayString = (String[]) request.getParameterValues(VacancyParameter.ID);

			ServiceFactory serviceFactory = ServiceFactory.getInstance();

			List<Vacancy> vacancyList = null;
			try {
				IVacancyService vacancyService = serviceFactory.getVacancyService();
				vacancyList = vacancyService.deleteVacancies(idResponceArrayString, person.getId());
				request.setAttribute(VACANCY_LIST, vacancyList);
			} catch (ValidationException e) {
				request.setAttribute(ERROR_MESSAGES, ErrorMessage.ERROR_MESSAGE_SELECTION_IS_EMPTY);
			} catch (ServiceException e) {
				throw new CommandException(e);
			}

			request.getRequestDispatcher(PageName.TABLE_VACANCY_PAGE).forward(request, response);

		} catch (ServletException | IOException e) {
			log.error(e);
			throw new CommandException(e);
		}

	}

}
