package by.epam.tc.hr_system.command.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epam.tc.hr_system.command.ICommand;
import by.epam.tc.hr_system.domain.Person;
import by.epam.tc.hr_system.exception.CommandException;
import by.epam.tc.hr_system.exception.ServiceException;
import by.epam.tc.hr_system.service.IUserService;
import by.epam.tc.hr_system.service.ServiceFactory;

public class RegistrationCommand implements ICommand {

	private static final String USER_DATE_OF_BIRTHDAY = "dateOfBirthday";
	private static final String USER_PHONE_NUMBER = "phoneNumber";
	private static final String USER_EMAIL = "email";
	private static final String USER_PATRONYMIC = "patronymic";
	private static final String USER_SURNAME = "surname";
	private static final String USER_NAME = "name";
	private static final String USER_ROLE = "role";
	private static final String USER_REPEATED_PASSWORD = "repeatedPassword";
	private static final String USER_PASSWORD = "password";
	private static final String USER_LOGIN = "login";

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {

		String login = request.getParameter(USER_LOGIN);
		String password = request.getParameter(USER_PASSWORD);
		String repeatedPassword = request.getParameter(USER_REPEATED_PASSWORD);
		String role = request.getParameter(USER_ROLE);
		
		String name = request.getParameter(USER_NAME);
		String surname = request.getParameter(USER_SURNAME);
		String patronymic = request.getParameter(USER_PATRONYMIC);
		String email = request.getParameter(USER_EMAIL);
		String phoneNumber = request.getParameter(USER_PHONE_NUMBER);
		String dateOfBirthday = request.getParameter(USER_DATE_OF_BIRTHDAY);

		ServiceFactory serviceFactory = ServiceFactory.getInstance();

		Person person = null;
		IUserService userService = null;
		try {
			userService = serviceFactory.getUserService();
			person = userService.registerUser(login, password, repeatedPassword, role, name, surname, patronymic, email, phoneNumber, dateOfBirthday);
		} catch (ServiceException e) {
			throw new CommandException("Error registeration login, password, role");
		}

	}

}
