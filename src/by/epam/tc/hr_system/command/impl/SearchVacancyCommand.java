package by.epam.tc.hr_system.command.impl;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import by.epam.tc.hr_system.command.ICommand;
import by.epam.tc.hr_system.domain.Vacancy;
import by.epam.tc.hr_system.exception.CommandException;
import by.epam.tc.hr_system.exception.ServiceException;
import by.epam.tc.hr_system.exception.validation.EmptyPropertyException;
import by.epam.tc.hr_system.exception.validation.IllegalSizeException;
import by.epam.tc.hr_system.exception.validation.ValidationException;
import by.epam.tc.hr_system.service.IVacancyService;
import by.epam.tc.hr_system.service.ServiceFactory;
import by.epam.tc.hr_system.util.MessageManager;
import by.epam.tc.hr_system.util.PageName;
import by.epam.tc.hr_system.util.parameter.VacancyParameter;

public class SearchVacancyCommand implements ICommand {

	private static final String SALARY_FROM = "salaryFrom";
	private static final String SALARY_TO = "salaryTo";
	private static final String ERROR_MESSAGE = "errorMessage";
	private static final String VACANCY_LIST = "vacancyList";

	private static final Logger log = Logger.getLogger(CreateResumeCommand.class);

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
		try {
			String titleVacancy = request.getParameter(VacancyParameter.TITLE_VACANCY);
			String employment = request.getParameter(VacancyParameter.EMPLOYMENT);
			String salaryFromString = request.getParameter(SALARY_FROM);
			String salaryToString = request.getParameter(SALARY_TO);

			ServiceFactory serviceFactory = ServiceFactory.getInstance();

			try {
				IVacancyService vacancyService = serviceFactory.getVacancyService();
				List<Vacancy> vacancyList = vacancyService.searchVacancyByParameters(titleVacancy, employment,
						salaryFromString, salaryToString);
				request.setAttribute(VACANCY_LIST, vacancyList);
			} catch (IllegalSizeException e) {
				request.setAttribute(ERROR_MESSAGE, MessageManager.ERROR_MESSAGE_ENTRY_VERY_LONG);
			} catch (EmptyPropertyException e) {
				request.setAttribute(ERROR_MESSAGE, MessageManager.ERROR_MESSAGE_REQUERED_FILEDS_MISSED);
			} catch (ServiceException | ValidationException e) {
				throw new CommandException(e);
			}

			request.getRequestDispatcher(PageName.SEARCH_VACANCY_PAGE).forward(request, response);

		} catch (ServletException | IOException e) {
			log.error(e);
			throw new CommandException(e);
		}

	}

}
