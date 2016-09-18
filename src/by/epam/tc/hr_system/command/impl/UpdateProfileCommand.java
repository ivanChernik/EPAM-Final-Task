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
import by.epam.tc.hr_system.exception.validation.IllegalSizeException;
import by.epam.tc.hr_system.exception.validation.ValidationException;
import by.epam.tc.hr_system.service.IPersonService;
import by.epam.tc.hr_system.service.ServiceFactory;
import by.epam.tc.hr_system.util.MessageManager;
import by.epam.tc.hr_system.util.PageName;
import by.epam.tc.hr_system.util.parameter.UserParameter;

public class UpdateProfileCommand implements ICommand {

	private static final String PERSON = "person";
	private static final String ERROR_MESSAGES = "errorMessage";
	private static final Logger log = Logger.getLogger(RegistrationCommand.class);

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {

		try {
			HttpSession session = request.getSession(true);
			Person person = (Person) session.getAttribute(PERSON);

			if (person == null) {
				request.getRequestDispatcher(PageName.INDEX_PAGE).forward(request, response);
				return;
			}

			String name = request.getParameter(UserParameter.NAME);
			String surname = request.getParameter(UserParameter.SURNAME);
			String patronymic = request.getParameter(UserParameter.MIDDLE_NAME);
			String email = request.getParameter(UserParameter.EMAIL);
			String phoneNumber = request.getParameter(UserParameter.PHONE_NUMBER);
			String dateOfBirthday = request.getParameter(UserParameter.DATE_OF_BIRTHDAY);

			ServiceFactory serviceFactory = ServiceFactory.getInstance();

			try {
				IPersonService userService = serviceFactory.getUserService();
				String role = person.getRole();
				person = userService.updateProfile(person.getId(), name, surname, patronymic, email, phoneNumber, dateOfBirthday);
				person.setRole(role);
				
				session.setAttribute(PERSON, person);
				request.getRequestDispatcher(PageName.VIEW_PROFILE_PAGE).forward(request, response);
				return;
			} catch (ServiceException e) {
				throw new CommandException(e);
			} catch (IllegalSizeException e) {
				request.setAttribute(ERROR_MESSAGES, MessageManager.ERROR_MESSAGE_ENTRY_VERY_LONG);
			} catch (IllegalDatesPeriodException e) {
				request.setAttribute(ERROR_MESSAGES, MessageManager.ERROR_MESSAGE_INVALID_DATE_VALUE);
			}  catch (EmptyPropertyException e) {
				request.setAttribute(ERROR_MESSAGES, MessageManager.ERROR_MESSAGE_REQUERED_FILEDS_MISSED);
			} catch (ValidationException e) {
				throw new CommandException(e);
			}

			// exception forwarding
			request.setAttribute(UserParameter.NAME, name);
			request.setAttribute(UserParameter.SURNAME, surname);
			request.setAttribute(UserParameter.MIDDLE_NAME, patronymic);
			request.setAttribute(UserParameter.EMAIL, email);
			request.setAttribute(UserParameter.PHONE_NUMBER, phoneNumber);
			request.setAttribute(UserParameter.DATE_OF_BIRTHDAY, dateOfBirthday);

			request.getRequestDispatcher(PageName.UPDATE_PROFILE_PAGE).forward(request, response);

		} catch (ServletException | IOException e) {
			log.error(e);
			throw new CommandException(e);
		}

	}


}
