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
import by.epam.tc.hr_system.exception.validation.ValidationException;
import by.epam.tc.hr_system.service.IVacancyResponceService;
import by.epam.tc.hr_system.service.ServiceFactory;
import by.epam.tc.hr_system.util.MessageManager;
import by.epam.tc.hr_system.util.PageName;

public class ShowResponceForVacancy implements ICommand {

	private static final String ERROR_MESSAGES = "errormessages";
	private static final String RESPONCE_LIST = "responceList";
	private static final String ID_VACANCY = "idVacancy";
	private static final String PERSON = "person";
	private static final Logger log = Logger.getLogger(ShowResponceForVacancy.class);

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
		
		try {
			HttpSession session = request.getSession(true);

			Person person = (Person) session.getAttribute(PERSON);

			if (person == null) {
				request.getRequestDispatcher(PageName.INDEX_PAGE).forward(request, response);
				return;
			}
			
			String idVacancyString = request.getParameter(ID_VACANCY);

			ServiceFactory serviceFactory = ServiceFactory.getInstance();

			List<VacancyResponce> responceList = null;
			try {
				 IVacancyResponceService responceService = serviceFactory.getVacancyResponceService();
				 responceList = responceService.getReponcesForVacancy(idVacancyString);
			} catch (ServiceException e) {	
				throw new CommandException(e);
			} catch (ValidationException e) {
				request.setAttribute(ERROR_MESSAGES, MessageManager.ERROR_MESSAGE_IMPOSSIBLE_ACTION);
			}

			request.setAttribute(RESPONCE_LIST,responceList);
			request.getRequestDispatcher(PageName.VACANCY_RESPONCE_PAGE).forward(request, response);

		} catch (ServletException | IOException e) {
			log.error(e);
			throw new CommandException(e);
		}
		
	}

}
