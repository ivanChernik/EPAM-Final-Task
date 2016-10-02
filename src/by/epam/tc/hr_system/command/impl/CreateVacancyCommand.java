package by.epam.tc.hr_system.command.impl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import by.epam.tc.hr_system.command.ICommand;
import by.epam.tc.hr_system.domain.Person;
import by.epam.tc.hr_system.exception.CommandException;
import by.epam.tc.hr_system.exception.ServiceException;
import by.epam.tc.hr_system.exception.validation.EmptyPropertyException;
import by.epam.tc.hr_system.exception.validation.IllegalEntriedValueException;
import by.epam.tc.hr_system.exception.validation.IllegalSizeException;
import by.epam.tc.hr_system.exception.validation.ValidationException;
import by.epam.tc.hr_system.service.IVacancyService;
import by.epam.tc.hr_system.service.ServiceFactory;
import by.epam.tc.hr_system.util.AuthorizationUser;
import by.epam.tc.hr_system.util.ErrorMessage;
import by.epam.tc.hr_system.util.PageName;
import by.epam.tc.hr_system.util.parameter.VacancyParameter;

/**
 * Command for creation vacancy.
 * 
 * @author Ivan Chernikau
 *
 */
public class CreateVacancyCommand implements ICommand {

	private static final String ERROR_MESSAGES = "errormessages";
	private static final Logger log = Logger.getLogger(CreateVacancyCommand.class);

	
	/**
	 * Invoke IvacancyService for creation and addiction new vacancy to appropriate HR-employee. 
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

			String name = request.getParameter(VacancyParameter.TITLE_VACANCY);
			String descrption = request.getParameter(VacancyParameter.DESCRIPTION);
			String shortDescription = request.getParameter(VacancyParameter.SHORT_DESCRIPTION);
			String requirement = request.getParameter(VacancyParameter.REQUIREMENT);
			String salary = request.getParameter(VacancyParameter.SALARY);
			String companyName = request.getParameter(VacancyParameter.COMPANY_NAME);
			String contactInformation = request.getParameter(VacancyParameter.CONTACT_DATA);
			String employment = request.getParameter(VacancyParameter.EMPLOYMENT);

			ServiceFactory serviceFactory = ServiceFactory.getInstance();

			try {
				IVacancyService vacancyService = serviceFactory.getVacancyService();
				vacancyService.addVacancy(name, descrption, shortDescription, requirement, salary, companyName,
						contactInformation, employment, person.getId());
				response.sendRedirect(PageName.TABLE_VACANCY_PAGE);
				return;

			} catch (IllegalSizeException e) {
				request.setAttribute(ERROR_MESSAGES, ErrorMessage.ERROR_MESSAGE_ENTRY_VERY_LONG);
			} catch (EmptyPropertyException e) {
				request.setAttribute(ERROR_MESSAGES, ErrorMessage.ERROR_MESSAGE_REQUERED_FILEDS_MISSED);
			} catch (IllegalEntriedValueException e) {
				request.setAttribute(ERROR_MESSAGES, ErrorMessage.ERROR_MESSAGE_ILLEGAL_ENTRIED_VALUE);

			} catch (ValidationException e) {
				request.setAttribute(ERROR_MESSAGES, ErrorMessage.ERROR_MESSAGE_VALIDATION_WAS_NOT_PASSED);

			} catch (ServiceException e) {
				throw new CommandException(e);
			}

			request.setAttribute(VacancyParameter.TITLE_VACANCY, name);
			request.setAttribute(VacancyParameter.DESCRIPTION, descrption);
			request.setAttribute(VacancyParameter.SHORT_DESCRIPTION, shortDescription);
			request.setAttribute(VacancyParameter.REQUIREMENT, requirement);
			request.setAttribute(VacancyParameter.COMPANY_NAME, companyName);
			request.setAttribute(VacancyParameter.CONTACT_DATA, contactInformation);
			request.setAttribute(VacancyParameter.SALARY, salary);
			request.getRequestDispatcher(PageName.CREATE_VACANCY_PAGE).forward(request, response);

		} catch (ServletException | IOException e) {
			log.error(e);
			throw new CommandException(e);
		}

	}

}
