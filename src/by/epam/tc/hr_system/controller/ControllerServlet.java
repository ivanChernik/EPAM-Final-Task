package by.epam.tc.hr_system.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(asyncSupported = true, urlPatterns = { "/ControllerServlet" })
public class ControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String COMMAND = "command";	
	
    public ControllerServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String commandName = request.getParameter(COMMAND);
		System.out.println("dsfffffffffffff");
	}

}
