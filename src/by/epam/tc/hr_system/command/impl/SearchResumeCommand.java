package by.epam.tc.hr_system.command.impl;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import by.epam.tc.hr_system.command.ICommand;
import by.epam.tc.hr_system.domain.Resume;
import by.epam.tc.hr_system.exception.CommandException;
import by.epam.tc.hr_system.exception.ServiceException;
import by.epam.tc.hr_system.exception.validation.EmptyPropertyException;
import by.epam.tc.hr_system.exception.validation.IllegalEntriedValueException;
import by.epam.tc.hr_system.exception.validation.IllegalSizeException;
import by.epam.tc.hr_system.exception.validation.ValidationException;
import by.epam.tc.hr_system.service.IResumeService;
import by.epam.tc.hr_system.service.ServiceFactory;
import by.epam.tc.hr_system.util.ErrorMessage;
import by.epam.tc.hr_system.util.PageName;
import by.epam.tc.hr_system.util.parameter.ResumeParamater;

/**
 * Command for searching resumes by parameters, entried by user.
 * 
 * @author Ivan Chernikau
 *
 */
public class SearchResumeCommand implements ICommand {

	private static final String RESULT = "result";
	private static final String RESUME_LIST = "resumeList";
	private static final String ERROR_MESSAGE = "errorMessage";
	private static final Logger log = Logger.getLogger(SearchResumeCommand.class);

	/**
	 * Invoke IResumeService to search resumes.
	 * 
	 * @param request
	 * @param response
	 * @throws CommandException
	 */
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
		try {
			String position = request.getParameter(ResumeParamater.POSITION);
			String kindEducation = request.getParameter(ResumeParamater.KIND_EDUCATION);

			ServiceFactory serviceFactory = ServiceFactory.getInstance();

			try {
				IResumeService resumeService = serviceFactory.getResumeService();
				List<Resume> resumeList = resumeService.searchResumeByParameter(position, kindEducation);
				request.setAttribute(RESUME_LIST, resumeList);
				request.setAttribute(RESULT, true);
			} catch (IllegalSizeException e) {
				request.setAttribute(ERROR_MESSAGE, ErrorMessage.ERROR_MESSAGE_ENTRY_VERY_LONG);
			} catch (EmptyPropertyException e) {
				request.setAttribute(ERROR_MESSAGE, ErrorMessage.ERROR_MESSAGE_REQUERED_FILEDS_MISSED);
			} catch (IllegalEntriedValueException e) {
				request.setAttribute(ERROR_MESSAGE, ErrorMessage.ERROR_MESSAGE_ILLEGAL_ENTRIED_VALUE);	
			} catch (ValidationException e) {
				request.setAttribute(ERROR_MESSAGE, ErrorMessage.ERROR_MESSAGE_VALIDATION_WAS_NOT_PASSED);	
			} catch (ServiceException e) {
				throw new CommandException(e);
			}

			request.getRequestDispatcher(PageName.SEARCH_RESUME_PAGE).forward(request, response);

		} catch (ServletException | IOException e) {
			log.error(e);
			throw new CommandException(e);
		}
	}

}
