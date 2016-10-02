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
import by.epam.tc.hr_system.exception.validation.IllegalSizeException;
import by.epam.tc.hr_system.exception.validation.ValidationException;
import by.epam.tc.hr_system.service.IResumeService;
import by.epam.tc.hr_system.service.IVacancyService;
import by.epam.tc.hr_system.service.ServiceFactory;
import by.epam.tc.hr_system.util.AuthorizationUser;
import by.epam.tc.hr_system.util.ErrorMessage;
import by.epam.tc.hr_system.util.PageName;
import by.epam.tc.hr_system.util.parameter.VacancyParameter;

/**
 * Command for removal resume of appropriate applicant
 * @author Ivan Chernikau
 *
 */
public class DeleteResumeCommand implements ICommand {
	
	private static final Logger log = Logger.getLogger(CreateVacancyCommand.class);

	/**
	 * Invoke IResumeService for removal resume. 
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

			ServiceFactory serviceFactory = ServiceFactory.getInstance();

			try {
				IResumeService resumeService = serviceFactory.getResumeService();
				resumeService.deleteResume(person.getId());
			} catch (ServiceException | ValidationException e) {
				throw new CommandException(e);
			}

			request.getRequestDispatcher(PageName.INDEX_APPLICANT_PAGE).forward(request, response);

		} catch (ServletException | IOException e) {
			log.error(e);
			throw new CommandException(e);
		}

	}

}
