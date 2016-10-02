package by.epam.tc.hr_system.command.impl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import by.epam.tc.hr_system.command.ICommand;
import by.epam.tc.hr_system.domain.Education;
import by.epam.tc.hr_system.domain.Person;
import by.epam.tc.hr_system.domain.PreviousPosition;
import by.epam.tc.hr_system.exception.CommandException;
import by.epam.tc.hr_system.exception.ServiceException;
import by.epam.tc.hr_system.exception.validation.EmptyPropertyException;
import by.epam.tc.hr_system.exception.validation.IllegalDatesPeriodException;
import by.epam.tc.hr_system.exception.validation.IllegalEntriedValueException;
import by.epam.tc.hr_system.exception.validation.IllegalSizeException;
import by.epam.tc.hr_system.exception.validation.ResumeDoesNotExistException;
import by.epam.tc.hr_system.exception.validation.ValidationException;
import by.epam.tc.hr_system.service.IResumeService;
import by.epam.tc.hr_system.service.ServiceFactory;
import by.epam.tc.hr_system.util.AuthorizationUser;
import by.epam.tc.hr_system.util.ErrorMessage;
import by.epam.tc.hr_system.util.PageName;
import by.epam.tc.hr_system.util.parameter.ResumeParamater;
/**
 * Command for addiction previos position to existing resume.
 * If entry contains invalid data we will be returned to 'Addiction position' screen
 * with error message.
 * @author Ivan Chernikau
 *
 */
public class AddPreviousPositionToResumeCommand implements ICommand {

	private static final String HTTP_GO_TO_RESUME = "./resume.jsp?idResume=";
	private static final String WORK_TO = "workTo";
	private static final String WORK_FROM = "workFrom";
	private static final String PREV_POSITION = "prevPosition";

	private static final String ERROR_MESSAGES = "errormessages";

	private static final Logger log = Logger.getLogger(CreateResumeCommand.class);

	/**
	 * Invoke IResumeService for validation params and addiction previous position
	 * If params contains the illegal data, they will be returned to view
	 * with appropriate error message
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

			String prevpositionName = request.getParameter(ResumeParamater.PREVIOS_POSITION);
			String workFrom = request.getParameter(ResumeParamater.WORK_FROM);
			String workTo = request.getParameter(ResumeParamater.WORK_TO);
			String workDescription = request.getParameter(ResumeParamater.WORK_DESCRIPTION);
			PreviousPosition prevPosition = new PreviousPosition(prevpositionName, workDescription);

			ServiceFactory serviceFactory = ServiceFactory.getInstance();

			try {
				IResumeService resumeService = serviceFactory.getResumeService();
				resumeService.addPreviousPosition(prevPosition, workFrom, workTo, person.getId());
				request.getRequestDispatcher(HTTP_GO_TO_RESUME + person.getId()).forward(request, response);
				return;
			} catch (ResumeDoesNotExistException e) {
				request.setAttribute(ERROR_MESSAGES, ErrorMessage.ERROR_MESSAGE_RESUME_DOES_NOT_EXIST);
			} catch (IllegalSizeException e) {
				request.setAttribute(ERROR_MESSAGES, ErrorMessage.ERROR_MESSAGE_ENTRY_VERY_LONG);

			} catch (IllegalDatesPeriodException e) {
				request.setAttribute(ERROR_MESSAGES, ErrorMessage.ERROR_MESSAGE_INVALID_DATE_VALUE);

			} catch (EmptyPropertyException e) {
				request.setAttribute(ERROR_MESSAGES, ErrorMessage.ERROR_MESSAGE_REQUERED_FILEDS_MISSED);
			} 
			catch (IllegalEntriedValueException e) {
				request.setAttribute(ERROR_MESSAGES, ErrorMessage.ERROR_MESSAGE_ILLEGAL_ENTRIED_VALUE);
			}
			catch (ValidationException e) {
				request.setAttribute(ERROR_MESSAGES, ErrorMessage.ERROR_MESSAGE_VALIDATION_WAS_NOT_PASSED);
			} catch (ServiceException e) {
				throw new CommandException(e);
			}

			request.setAttribute(PREV_POSITION, prevPosition);
			request.setAttribute(WORK_FROM, workFrom);
			request.setAttribute(WORK_TO, workTo);

			request.getRequestDispatcher(PageName.ADDICTION_PREVIOUS_POSITION_PAGE).forward(request, response);

		} catch (ServletException | IOException e) {
			log.error(e);
			throw new CommandException(e);
		}
	}

}
