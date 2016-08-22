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
import by.epam.tc.hr_system.exception.validation.IllegalDatesPeriodException;
import by.epam.tc.hr_system.exception.validation.IllegalStringLengtnException;
import by.epam.tc.hr_system.exception.validation.LoginAlreadyExistsExeption;
import by.epam.tc.hr_system.exception.validation.PasswordsNotEqualException;
import by.epam.tc.hr_system.exception.validation.ValidationException;
import by.epam.tc.hr_system.service.IUserService;
import by.epam.tc.hr_system.service.ServiceFactory;
import by.epam.tc.hr_system.util.RoleDispatcher;
import by.epam.tc.hr_system.util.MessageManager;
import by.epam.tc.hr_system.util.PageName;
import by.epam.tc.hr_system.util.parameter.UserParameter;

public class RegistrationCommand implements ICommand {

	private static final String PERSON = "person";
	private static final String ERROR_MESSAGES = "errorMessage";
	private static final Logger log = Logger.getLogger(RegistrationCommand.class);

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {

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

			try {
				IUserService userService = serviceFactory.getUserService();
				person = userService.registerUser(login, password, repeatedPassword, role, name, surname, patronymic,
						email, phoneNumber, dateOfBirthday);
				RoleDispatcher roleDispatcher = RoleDispatcher.getInstance();
				session.setAttribute(PERSON, person);
				roleDispatcher.forwardToIndexByRole(request, response, role);
				return;
			} catch (ServiceException e) {
				throw new CommandException(e);
			} catch (IllegalStringLengtnException e) {
				request.setAttribute(ERROR_MESSAGES, MessageManager.ERROR_MESSAGE_ENTRY_VERY_LONG);
			} catch (IllegalDatesPeriodException e) {
				request.setAttribute(ERROR_MESSAGES, MessageManager.ERROR_MESSAGE_INVALID_DATE_VALUE);
			} catch (PasswordsNotEqualException e) {
				request.setAttribute(ERROR_MESSAGES, MessageManager.ERROR_MESSAGE_INVALID_REPETED_PASSWORD);
			} catch (LoginAlreadyExistsExeption e) {
				request.setAttribute(ERROR_MESSAGES, MessageManager.ERROR_MESSAGE_LOGIN_ALREADY_EXISTS);
			} catch (ValidationException e) {
				request.setAttribute(ERROR_MESSAGES, MessageManager.ERROR_MESSAGE_REQUERED_FILEDS_MISSED);
			}
			
			//exception forwarding
			request.setAttribute(UserParameter.LOGIN,login);
			request.setAttribute(UserParameter.PASSWORD,password);
			request.setAttribute(UserParameter.REPEATED_PASSWORD,repeatedPassword);
			request.setAttribute(UserParameter.ROLE,role);
			request.setAttribute(UserParameter.NAME,name);
			request.setAttribute(UserParameter.SURNAME, surname);
			request.setAttribute(UserParameter.PATRONYMIC, patronymic);
			request.setAttribute(UserParameter.EMAIL,email);
			request.setAttribute(UserParameter.PHONE_NUMBER,phoneNumber);
			request.setAttribute(UserParameter.DATE_OF_BIRTHDAY,dateOfBirthday);
			
			request.getRequestDispatcher(PageName.INDEX_PAGE).forward(request, response);
			
		} catch (ServletException | IOException e) {
			log.error(e);
			throw new CommandException(e);
		}

	}

}
