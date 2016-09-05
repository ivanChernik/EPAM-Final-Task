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
import by.epam.tc.hr_system.util.MessageManager;
import by.epam.tc.hr_system.util.PageName;
import by.epam.tc.hr_system.util.parameter.VacancyParameter;

public class DeleteVacancyCommand implements ICommand {
	private static final String VACANCY_LIST = "vacancyList";
	private static final String ERROR_MESSAGES = "errormessages";
	private static final String PERSON = "person";
	private static final Logger log = Logger.getLogger(CreateVacancyCommand.class);

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
		try {
			HttpSession session = request.getSession(true);
			Person person = (Person) session.getAttribute(PERSON);
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
			} catch (ServiceException e) {
				throw new CommandException(e);
			} catch (ValidationException e) {
				request.setAttribute(ERROR_MESSAGES, MessageManager.ERROR_MESSAGE_SELECTION_IS_EMPTY);
			}

			request.getRequestDispatcher(PageName.TABLE_VACANCY_PAGE).forward(request, response);

		} catch (ServletException | IOException e) {
			log.error(e);
			throw new CommandException(e);
		}

	}

}
