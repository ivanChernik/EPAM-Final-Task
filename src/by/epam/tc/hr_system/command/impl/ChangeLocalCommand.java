package by.epam.tc.hr_system.command.impl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import by.epam.tc.hr_system.command.ICommand;
import by.epam.tc.hr_system.exception.CommandException;

public class ChangeLocalCommand implements ICommand {

	private static final String LOCAL = "local";
	private static final Logger log = Logger.getLogger(ChangeLocalCommand.class);

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
		request.getSession(true).setAttribute(LOCAL, request.getParameter(LOCAL));
		String url = request.getRequestURL().toString();
		
		url = url.substring(0, url.lastIndexOf("/"));
		
		String queryString = ((HttpServletRequest) request).getQueryString();
		if (queryString != null) {
			url = url + "?" + queryString;
		}
		
		 try {
			response.sendRedirect(url);
		} catch (IOException e) {
			log.error("Failed send redirect", e);
			throw new CommandException(e);
		}
		 
	}

}
