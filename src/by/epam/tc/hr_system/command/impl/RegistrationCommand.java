package by.epam.tc.hr_system.command.impl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import by.epam.tc.hr_system.command.ICommand;
import by.epam.tc.hr_system.domain.Person;
import by.epam.tc.hr_system.exception.ServiceException;
import by.epam.tc.hr_system.service.IUserService;
import by.epam.tc.hr_system.service.ServiceFactory;
import by.epam.tc.hr_system.util.MessageManager;
import by.epam.tc.hr_system.util.PageName;
import by.epam.tc.hr_system.util.parameter.UserParameter;

public class RegistrationCommand implements ICommand {

	private static final String ERROR_REGISTRATION = "Error registration";
	private static final Logger log = Logger.getLogger(RegistrationCommand.class);

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {

		try {
			HttpSession session = request.getSession(true);

			String login = request.getParameter(UserParameter.LOGIN);
			String password = request.getParameter(UserParameter.PASSWORD);
			String repeatedPassword = request.getParameter(UserParameter.REPEATED_PASSWORD);
			String role = request.getParameter(UserParameter.ROLE);

			String name = request.getParameter(UserParameter.NAME);
			String surname = request.getParameter(UserParameter.SURNAME);
			String patronymic = request.getParameter(UserParameter.PATRONYMIC);
			String email = request.getParameter(UserParameter.EMAIL);
			String phoneNumber = request.getParameter(UserParameter.PHONE_NUMBER);
			String dateOfBirthday = request.getParameter(UserParameter.DATE_OF_BIRTHDAY);

			ServiceFactory serviceFactory = ServiceFactory.getInstance();

			Person person = null;
			IUserService userService = null;
			String errorMessage = null;
			try {
				userService = serviceFactory.getUserService();
				person = userService.registerUser(login, password, repeatedPassword, role, name, surname, patronymic,
						email, phoneNumber, dateOfBirthday);
			} catch (ServiceException e) {

				if (e.getMessage().equals(MessageManager.MESSAGE_INVALID_PASSWORD)) {
					errorMessage = MessageManager.MESSAGE_INVALID_PASSWORD;
					request.setAttribute("errorMessage", errorMessage);
					request.getRequestDispatcher(PageName.INDEX_PAGE).forward(request, response);
					return;
				}

				if (e.getMessage().equals(MessageManager.MESSAGE_LOGIN_ALREADY_EXISTS)) {
					errorMessage = MessageManager.MESSAGE_LOGIN_ALREADY_EXISTS;
					request.setAttribute("errorMessage", errorMessage);
					request.getRequestDispatcher(PageName.INDEX_PAGE).forward(request, response);
					return;
				}

				request.setAttribute("errorMessage", ERROR_REGISTRATION);
				request.getRequestDispatcher(PageName.INDEX_PAGE).forward(request, response);
				return;
			} finally {
				session.setAttribute("person", person);
			}

			if (person.getRole().equals(Person.APPLICANT_ROLE)) {
				request.getRequestDispatcher(PageName.INDEX_APPLICANT_PAGE).forward(request, response);
			} else if (person.getRole().equals(Person.HR_ROLE)) {
				request.getRequestDispatcher(PageName.INDEX_HR_PAGE).forward(request, response);
			}

		} catch (ServletException e) {
			log.error(e);
		} catch (IOException e) {
			log.error(e);
		}

	}

}
