
package by.epam.tc.hr_system.command.impl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import by.epam.tc.hr_system.command.ICommand;
import by.epam.tc.hr_system.domain.Person;
import by.epam.tc.hr_system.domain.Vacancy;
import by.epam.tc.hr_system.domain.VacancyResponse;
import by.epam.tc.hr_system.exception.CommandException;
import by.epam.tc.hr_system.exception.ServiceException;
import by.epam.tc.hr_system.exception.validation.ResponseAlreadyExistsException;
import by.epam.tc.hr_system.exception.validation.ResumeDoesNotExistException;
import by.epam.tc.hr_system.exception.validation.ValidationException;
import by.epam.tc.hr_system.service.IVacancyResponseService;
import by.epam.tc.hr_system.service.IVacancyService;
import by.epam.tc.hr_system.service.ServiceFactory;
import by.epam.tc.hr_system.util.AuthorizationUser;
import by.epam.tc.hr_system.util.ErrorMessage;
import by.epam.tc.hr_system.util.PageName;
import by.epam.tc.hr_system.util.parameter.VacancyParameter;
/**
 * Command for 'sending' (creation responce to vacancy) resume to vacancy.
 * with error message.
 * @author Ivan Chernikau
 *
 */
public class ApplyForJobCommand implements ICommand {

	private static final String VACANCY = "vacancy";
	private static final String ERROR_MESSAGES = "errormessages";

	private static final Logger log = Logger.getLogger(ApplyForJobCommand.class);

	/**
	 * Invoke IVacancyService for obtaining vacancy.
	 * Invoke IVacancyResponceService for checking to existing response with the resume
	 * to vacancy. If response does not exist, invoke IVacancyResponceService for creation responce
	 * @param request 
	 * @param response 
	 * @throws CommandException
	 */
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
		try {
			Person person = AuthorizationUser.getPersonInSession(request);

			if (person == null) {
				request.getRequestDispatcher(PageName.INDEX_PAGE).forward(request, response);
				return;
			}

			String idVacancy = request.getParameter(VacancyParameter.ID);

			ServiceFactory serviceFactory = ServiceFactory.getInstance();
			Vacancy vacancy = null;
			try {
				IVacancyService vacancyService = serviceFactory.getVacancyService();
				vacancy = vacancyService.getVacancyByID(idVacancy);
				IVacancyResponseService vacancyResponceService = serviceFactory.getVacancyResponceService();
				vacancyResponceService.checkResponceToVacancy(person.getId(), idVacancy);
				vacancyResponceService.addResponceToVacancy(person.getId(),idVacancy);

			} catch (ServiceException e) {
				throw new CommandException(e);
			} catch (ResumeDoesNotExistException e) {
				request.setAttribute(VACANCY, vacancy);
				request.setAttribute(ERROR_MESSAGES, ErrorMessage.ERROR_MESSAGE_RESUME_DOES_NOT_EXIST);
				request.getRequestDispatcher(PageName.VACANCY_PAGE).forward(request, response);
				return;
			} catch (ResponseAlreadyExistsException e) {
				request.setAttribute(VACANCY, vacancy);
				request.setAttribute(ERROR_MESSAGES, ErrorMessage.ERROR_MESSAGE_RESPONCE_ALREDY_EXISTS);
				request.getRequestDispatcher(PageName.VACANCY_PAGE).forward(request, response);
				return;
			} catch (ValidationException e) {
				request.setAttribute(ERROR_MESSAGES, ErrorMessage.ERROR_MESSAGE_VALIDATION_WAS_NOT_PASSED);
				request.getRequestDispatcher(PageName.VACANCY_PAGE).forward(request, response);
				return;
			}

			request.getRequestDispatcher(PageName.INDEX_APPLICANT_PAGE).forward(request, response);

		} catch (ServletException | IOException e) {
			log.error(e);
			throw new CommandException(e);
		}

	}
	

}
