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
import by.epam.tc.hr_system.exception.validation.EmptyPropertyException;
import by.epam.tc.hr_system.exception.validation.ValidationException;
import by.epam.tc.hr_system.service.IVacancyResponseService;
import by.epam.tc.hr_system.service.IVacancyService;
import by.epam.tc.hr_system.service.ServiceFactory;
import by.epam.tc.hr_system.util.ErrorMessage;
import by.epam.tc.hr_system.util.PageName;
import by.epam.tc.hr_system.util.parameter.VacancyParameter;
import by.epam.tc.hr_system.util.validation.AuthorizingUser;

/**
 * Command for change response status (viewed, rejection and etc).
 * @see VacancyResponse - contains statuses
 * 
 * It's possible to change status for one and more responses.
 * @author Ivan Chernikau
 *
 */
public class ChangeResponceStatusCommand implements ICommand {

	private static final String HTTP_VACANCY_NAME = "&vacancyName=";
	private static final String HTTP_GO_TO_VACANCY = "./Controller?command=show-responce-to-vacancy&idVacancy=";
	private static final String VACANCY_NAME = "vacancyName";
	private static final String STATUS = "status";
	private static final String RESPONCE_LIST = "responceList";
	private static final String ID_RESPONCE = "idResponce";
	private static final String ERROR_MESSAGES = "errormessages";
	private static final Logger log = Logger.getLogger(CreateVacancyCommand.class);

	/**
	 * Invoke IVacancyResponceService for changing status
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

			String[] idResponceArrayString = (String[]) request.getParameterValues(ID_RESPONCE);
			String status = request.getParameter(STATUS);
			String idVacancy = request.getParameter(VacancyParameter.ID);
			String vacancyName = request.getParameter(VACANCY_NAME);

			ServiceFactory serviceFactory = ServiceFactory.getInstance();

			try {
				List<VacancyResponse> responceList = null;

				IVacancyResponseService responceService = serviceFactory.getVacancyResponceService();
				responceService.changeResponceStatus(idResponceArrayString, status, idVacancy);
				request.setAttribute(RESPONCE_LIST, responceList);
			} catch (ValidationException e) {
				request.setAttribute(ERROR_MESSAGES, ErrorMessage.ERROR_MESSAGE_SELECTION_IS_EMPTY);
			} catch (ServiceException e) {
				throw new CommandException(e);
			}

			request.getRequestDispatcher(HTTP_GO_TO_VACANCY + idVacancy
					+ HTTP_VACANCY_NAME + vacancyName).forward(request, response);

		} catch (ServletException | IOException e) {
			log.error(e);
			throw new CommandException(e);
		}

	}

}
