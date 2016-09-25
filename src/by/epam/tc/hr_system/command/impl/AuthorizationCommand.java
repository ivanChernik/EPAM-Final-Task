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
import by.epam.tc.hr_system.exception.validation.ValidationException;
import by.epam.tc.hr_system.service.IPersonService;
import by.epam.tc.hr_system.service.ServiceFactory;
import by.epam.tc.hr_system.util.PageName;
import by.epam.tc.hr_system.util.ScreenRoleDispatcher;
import by.epam.tc.hr_system.util.parameter.UserParameter;

/**
 * Command for athorization user.
 *
 * @author Ivan Chernikau
 *
 */
public class AuthorizationCommand implements ICommand {

	private static final String PERSON = "person";
	private static final Logger log = Logger.getLogger(AuthorizationCommand.class);

	/**
	 * Invoke IPersonService for authorization user.
	 * 
	 * @param request
	 * @param response
	 * @throws CommandException
	 */
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
		try {
			HttpSession session = request.getSession(false);

			String login = request.getParameter(UserParameter.LOGIN);
			String password = request.getParameter(UserParameter.PASSWORD);

			ServiceFactory serviceFactory = ServiceFactory.getInstance();

			Person person = null;

			try {
				IPersonService userService = serviceFactory.getUserService();
				person = userService.authorizePerson(login, password);
			} catch (ServiceException e) {
				request.getRequestDispatcher(PageName.ERROR_505_PAGE).forward(request, response);
				return;
			} catch (ValidationException e) {
				request.getRequestDispatcher(PageName.INDEX_PAGE).forward(request, response);
				return;
			}

			if (person == null) {
				request.getRequestDispatcher(PageName.INDEX_PAGE).forward(request, response);
				return;
			}

			ScreenRoleDispatcher roleDispatcher = ScreenRoleDispatcher.getInstance();
			session.setAttribute(PERSON, person);
			roleDispatcher.forwardToIndexByRole(request, response, person.getRole());

		} catch (ServletException | IOException e) {
			log.error(e);
			throw new CommandException(e);
		}

	}

}
