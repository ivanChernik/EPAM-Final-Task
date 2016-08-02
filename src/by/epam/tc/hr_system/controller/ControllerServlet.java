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


@WebServlet(asyncSupported = true, urlPatterns = { "/ControllerServlet" })
@MultipartConfig
public class ControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String COMMAND = "command";	
	private static final Logger log = Logger.getLogger(ControllerServlet.class);
	
    public ControllerServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String commandName = request.getParameter(COMMAND);
		ICommand command;
		try {
			command = CommandHelper.getInstance().getCommand(commandName);
			command.execute(request, response);
		} catch (CommandException e) {
			String url = request.getRequestURL().toString();
			url = url.substring(0, url.lastIndexOf("/"));
			
			String queryString = ((HttpServletRequest) request).getQueryString();
			if (queryString != null) {
				url = url + "?" + queryString;
			}
			
			 try {
				response.sendRedirect(url);
			} catch (IOException eIO) {
				log.error("Failed send redirect", eIO);
			}
		}	
	}

}
