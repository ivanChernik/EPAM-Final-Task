package by.epam.tc.hr_system.command.impl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import by.epam.tc.hr_system.command.ICommand;
import by.epam.tc.hr_system.exception.CommandException;
import by.epam.tc.hr_system.util.PageName;

/**
 * Command for logging out user. (clean 'person' attribute from session)
 * 
 * @author Ivan Chernikau
 *
 */

public class LogOutCommand implements ICommand {

	private static final String PERSON = "person";
	private static final Logger log = Logger.getLogger(LogOutCommand.class);

	/**
	 * Clean 'person' attribute from session
	 * 
	 * @param request
	 * @param response
	 * @throws CommandException
	 */
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {

		try {
			HttpSession session = request.getSession(false);

			if (session.getAttribute(PERSON) != null) {
				session.removeAttribute(PERSON);
			}

			response.sendRedirect(PageName.INDEX_PAGE);

		} catch (IOException e) {
			log.error(e);
			throw new CommandException(e);
		}

	}
}
