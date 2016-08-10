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
import by.epam.tc.hr_system.service.IUserService;
import by.epam.tc.hr_system.service.ServiceFactory;
import by.epam.tc.hr_system.util.PageName;
import by.epam.tc.hr_system.util.parameter.UserParameter;

public class AuthorizationCommand implements ICommand {

	private static final Logger log = Logger.getLogger(AuthorizationCommand.class);
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
		try {
			HttpSession session = request.getSession(true);

			String login = request.getParameter(UserParameter.LOGIN);
			String password = request.getParameter(UserParameter.PASSWORD);

			ServiceFactory serviceFactory = ServiceFactory.getInstance();

			Person person = null;
			IUserService userService = null;

			try {
				userService = serviceFactory.getUserService();
				person = userService.authorizePerson(login, password);
			} catch (ServiceException e) {
				
				request.getRequestDispatcher(PageName.INDEX_PAGE).forward(request, response);
				return;
			} 	
			
			session.setAttribute("person", person);

			if (person.getRole().equals(Person.APPLICANT_ROLE)) {
				request.getRequestDispatcher(PageName.INDEX_APPLICANT_PAGE).forward(request, response);
			}
			if (person.getRole().equals(Person.HR_ROLE)) {
				request.getRequestDispatcher(PageName.INDEX_HR_PAGE).forward(request, response);
			}

		} catch (ServletException | IOException e) {
			log.error(e);
			throw new CommandException(e);
		}

	}

}
