package by.epam.tc.hr_system.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import by.epam.tc.hr_system.command.ICommand;
import by.epam.tc.hr_system.command.impl.RegistrationCommand;
import by.epam.tc.hr_system.controller.helper.CommandHelper;
import by.epam.tc.hr_system.exception.CommandException;
import by.epam.tc.hr_system.util.PageName;

/**
 * HttpServlet receives all data from view (JSP-pages) and invokes appropriate
 * command.
 * 
 * @author Ivan Chernikau
 *
 */

@WebServlet(asyncSupported = true, urlPatterns = { "/Controller" })
@MultipartConfig
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String COMMAND = "command";

	public Controller() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * Forward HttpServletRequest request, HttpServletResponse response for
	 * appropriate command
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException,
	 *             IOException
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String commandName = request.getParameter(COMMAND);
		ICommand command;
		try {
			command = CommandHelper.getInstance().getCommand(commandName);
			command.execute(request, response);
		} catch (CommandException e) {
			request.getRequestDispatcher(PageName.ERROR_505_PAGE).forward(request, response);
			//response.sendRedirect(PageName.ERROR_505_PAGE);
		}
	}

}
