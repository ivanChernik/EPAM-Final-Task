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
import by.epam.tc.hr_system.util.UserData;

public class RegistrationCommand implements ICommand {

	private static final Logger log = Logger.getLogger(RegistrationCommand.class);

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {

		try {
			HttpSession session = request.getSession(true);

			String login = request.getParameter(UserData.USER_LOGIN);
			String password = request.getParameter(UserData.USER_PASSWORD);
			String repeatedPassword = request.getParameter(UserData.USER_REPEATED_PASSWORD);
			String role = request.getParameter(UserData.USER_ROLE);

			String name = request.getParameter(UserData.USER_NAME);
			String surname = request.getParameter(UserData.USER_SURNAME);
			String patronymic = request.getParameter(UserData.USER_PATRONYMIC);
			String email = request.getParameter(UserData.USER_EMAIL);
			String phoneNumber = request.getParameter(UserData.USER_PHONE_NUMBER);
			String dateOfBirthday = request.getParameter(UserData.USER_DATE_OF_BIRTHDAY);

			ServiceFactory serviceFactory = ServiceFactory.getInstance();

			Person person = null;
			IUserService userService = null;
			String errorMessage = null;
			try {
				userService = serviceFactory.getUserService();
				person = userService.registerUser(login, password, repeatedPassword, role, name, surname, patronymic,
						email, phoneNumber, dateOfBirthday);
			} catch (ServiceException e) {

				if (e.getMessage().equals(IUserService.MESSAGE_INVALID_PASSWORD)) {
					errorMessage = IUserService.MESSAGE_INVALID_PASSWORD;
				}

				if (e.getMessage().equals(IUserService.MESSAGE_LOGIN_ALREADY_EXISTS)) {
					errorMessage = IUserService.MESSAGE_LOGIN_ALREADY_EXISTS;
				}

				request.setAttribute("errorMessage", errorMessage);
				request.getRequestDispatcher(PageName.INDEX_PAGE).forward(request, response);
				return;
			} finally {
				session.setAttribute("person", person);
			}

			if (person.getRole().equals(Person.APPLICANT_ROLE)) {
				request.getRequestDispatcher(PageName.INDEX_PAGE_APPLICANT).forward(request, response);
			} else if (person.getRole().equals(Person.HR_ROLE)) {
				request.getRequestDispatcher(PageName.INDEX_PAGE_HR).forward(request, response);
			}

		} catch (ServletException e) {
			log.error(e);
		} catch (IOException e) {
			log.error(e);
		}

	}

}
