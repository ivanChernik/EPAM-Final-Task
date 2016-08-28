package by.epam.tc.hr_system.command.impl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import by.epam.tc.hr_system.command.ICommand;
import by.epam.tc.hr_system.exception.CommandException;

public class ChangeLocalCommand implements ICommand {

	private static final String LITERAL_QUESTION = "?";
	private static final String REFERER = "Referer";
	private static final String ROOT_PATH = "./";
	private static final String PAGE_NAME = "pageName";
	private static final String LOCAL = "local";
	private static final Logger log = Logger.getLogger(ChangeLocalCommand.class);

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {

		HttpSession session = request.getSession(true);
		session.setAttribute(LOCAL, request.getParameter(LOCAL));

		String url = ROOT_PATH;
		if (session.getAttribute(PAGE_NAME) != null) {
			url = url + session.getAttribute(PAGE_NAME);
		}

		String referer = request.getHeader(REFERER);

		if (referer != null && referer.lastIndexOf(LITERAL_QUESTION) > 0) {
			String queryString = referer.substring(referer.lastIndexOf(LITERAL_QUESTION));
			url += queryString;
		}

		try {
			response.sendRedirect(url);
		} catch (IOException e) {
			log.error("Failed send redirect", e);
			throw new CommandException(e);
		}

	}

}
