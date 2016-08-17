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
import by.epam.tc.hr_system.domain.VacancyResponce;
import by.epam.tc.hr_system.exception.CommandException;
import by.epam.tc.hr_system.exception.ServiceException;
import by.epam.tc.hr_system.exception.validation.ValidationExeception;
import by.epam.tc.hr_system.service.IVacancyResponceService;
import by.epam.tc.hr_system.service.IVacancyService;
import by.epam.tc.hr_system.service.ServiceFactory;
import by.epam.tc.hr_system.util.PageName;
import by.epam.tc.hr_system.util.parameter.VacancyParameter;

public class ChangeResponceStatusCommand implements ICommand {
	private static final String VACANCY_NAME = "vacancyName";
	private static final String STATUS = "status";
	private static final String RESPONCE_LIST = "responceList";
	private static final String ID_RESPONCE = "idResponce";
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

			String[] idResponceArrayString = (String[]) request.getParameterValues(ID_RESPONCE);
			String status = request.getParameter(STATUS);
			String idVacancy = request.getParameter(VacancyParameter.ID);
			String vacancyName = request.getParameter(VACANCY_NAME);

			ServiceFactory serviceFactory = ServiceFactory.getInstance();

			try {
				List<VacancyResponce> responceList = null;

				IVacancyResponceService responceService = serviceFactory.getVacancyResponceService();

					responceService.changeResponceStatus(idResponceArrayString, status, idVacancy);
				
				request.setAttribute(RESPONCE_LIST, responceList);
			} catch (ServiceException e) {
				request.getRequestDispatcher(PageName.ERROR_505_PAGE).forward(request, response);
				return;
			} catch (ValidationExeception e) {
				//непридумал
			}

			request.getRequestDispatcher("./Controller?command=show-responce-to-vacancy&idVacancy=" + idVacancy
					+ "&vacancyName=" + vacancyName).forward(request, response);

		} catch (ServletException | IOException e) {
			log.error(e);
			throw new CommandException(e);
		}

	}

}
