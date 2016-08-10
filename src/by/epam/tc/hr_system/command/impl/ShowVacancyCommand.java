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
import by.epam.tc.hr_system.service.IVacancyService;
import by.epam.tc.hr_system.service.ServiceFactory;
import by.epam.tc.hr_system.util.PageName;
import by.epam.tc.hr_system.util.parameter.VacancyParameter;

public class ShowVacancyCommand implements ICommand {

	private static final String VACANCY = "vacancy";
	private static final Logger log = Logger.getLogger(ShowVacancyCommand.class);

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {

		try {
			int vacancyID = Integer.parseInt(request.getParameter(VacancyParameter.ID));

			ServiceFactory serviceFactory = ServiceFactory.getInstance();

			try {
				IVacancyService vacancyService = serviceFactory.getVacancyService();
				Vacancy vacancy = vacancyService.getVacancyByID(vacancyID);
				request.setAttribute(VACANCY, vacancy);
			} catch (ServiceException e) {

			}

			request.getRequestDispatcher(PageName.VACANCY_PAGE).forward(request, response);
		} catch (ServletException | IOException e) {
			log.error(e);
			throw new CommandException(e);
		}

	}
}
