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
import by.epam.tc.hr_system.exception.validation.IllegalDatesPeriodException;
import by.epam.tc.hr_system.exception.validation.IllegalEntriedValueException;
import by.epam.tc.hr_system.exception.validation.IllegalMinSizeException;
import by.epam.tc.hr_system.exception.validation.IllegalSizeException;
import by.epam.tc.hr_system.exception.validation.LoginAlreadyExistsExeption;
import by.epam.tc.hr_system.exception.validation.PasswordsNotEqualException;
import by.epam.tc.hr_system.exception.validation.ValidationException;
import by.epam.tc.hr_system.service.IPersonService;
import by.epam.tc.hr_system.service.ServiceFactory;
import by.epam.tc.hr_system.util.ScreenRoleDispatcher;
import by.epam.tc.hr_system.util.ErrorMessage;
import by.epam.tc.hr_system.util.PageName;
import by.epam.tc.hr_system.util.parameter.UserParameter;

/**
 * Command for registration appropriate type of user.
 * User can register only with one role.
 * 
 * @see Person - type of user roles.
 * 
 * @author Ivan Chernikau
 *
 */
public class RegistrationCommand implements ICommand {

	private static final String PERSON = "person";
	private static final String ERROR_MESSAGES = "errorMessage";
	private static final Logger log = Logger.getLogger(RegistrationCommand.class);

	/**
	 * Invoke IPersonService for registration.
	 * Invoke ScreenRoleDispatcher for forwarding to appropriate screen.
	 * 
	 * @param request
	 * @param response
	 * @throws CommandException
	 */
	
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
			String patronymic = request.getParameter(UserParameter.MIDDLE_NAME);
			String email = request.getParameter(UserParameter.EMAIL);
			String phoneNumber = request.getParameter(UserParameter.PHONE_NUMBER);
			String dateOfBirthday = request.getParameter(UserParameter.DATE_OF_BIRTHDAY);

			ServiceFactory serviceFactory = ServiceFactory.getInstance();

			Person person = null;

			try {
				IPersonService userService = serviceFactory.getUserService();
				person = userService.registerUser(login, password, repeatedPassword, role, name, surname, patronymic,
						email, phoneNumber, dateOfBirthday);
				
				session.setAttribute(PERSON, person);
				ScreenRoleDispatcher roleDispatcher = ScreenRoleDispatcher.getInstance();
				roleDispatcher.forwardToIndexByRole(request, response, role);
				return;
			} catch (IllegalSizeException e) {
				request.setAttribute(ERROR_MESSAGES, ErrorMessage.ERROR_MESSAGE_ENTRY_VERY_LONG);
			} catch (IllegalDatesPeriodException e) {
				request.setAttribute(ERROR_MESSAGES, ErrorMessage.ERROR_MESSAGE_INVALID_DATE_VALUE);
			} catch (PasswordsNotEqualException e) {
				request.setAttribute(ERROR_MESSAGES, ErrorMessage.ERROR_MESSAGE_INVALID_REPETED_PASSWORD);
			} catch (LoginAlreadyExistsExeption e) {
				request.setAttribute(ERROR_MESSAGES, ErrorMessage.ERROR_MESSAGE_LOGIN_ALREADY_EXISTS);
			} catch (EmptyPropertyException e) {
				request.setAttribute(ERROR_MESSAGES, ErrorMessage.ERROR_MESSAGE_REQUERED_FILEDS_MISSED);
			} catch (IllegalEntriedValueException e) {
				request.setAttribute(ERROR_MESSAGES, ErrorMessage.ERROR_MESSAGE_ILLEGAL_ENTRIED_VALUE);
				
			} catch (IllegalMinSizeException e) {
				request.setAttribute(ERROR_MESSAGES, ErrorMessage.ERROR_MESSAGE_ENTRY_SHOULD_BE_LONGER);
				
			} catch (ValidationException e) {
				request.setAttribute(ERROR_MESSAGES, ErrorMessage.ERROR_MESSAGE_VALIDATION_WAS_NOT_PASSED);
				
			} catch (ServiceException e) {
				throw new CommandException(e);
			}

			// exception forwarding
			request.setAttribute(UserParameter.LOGIN, login);
			request.setAttribute(UserParameter.PASSWORD, password);
			request.setAttribute(UserParameter.REPEATED_PASSWORD, repeatedPassword);
			request.setAttribute(UserParameter.ROLE, role);
			request.setAttribute(UserParameter.NAME, name);
			request.setAttribute(UserParameter.SURNAME, surname);
			request.setAttribute(UserParameter.MIDDLE_NAME, patronymic);
			request.setAttribute(UserParameter.EMAIL, email);
			request.setAttribute(UserParameter.PHONE_NUMBER, phoneNumber);
			request.setAttribute(UserParameter.DATE_OF_BIRTHDAY, dateOfBirthday);

			request.getRequestDispatcher(PageName.INDEX_PAGE).forward(request, response);

		} catch (ServletException | IOException e) {
			log.error(e);
			throw new CommandException(e);
		}

	}

}
