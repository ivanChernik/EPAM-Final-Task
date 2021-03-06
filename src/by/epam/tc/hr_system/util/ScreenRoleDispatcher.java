package by.epam.tc.hr_system.util;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import by.epam.tc.hr_system.domain.Person;

/**
 * Dispatches the page after user authorization.
 * 
 * @author Ivan Chernikau
 *
 */
public class ScreenRoleDispatcher {
	private Map<String, String> pages = new HashMap<>();
	private static final ScreenRoleDispatcher instance = new ScreenRoleDispatcher();

	private ScreenRoleDispatcher() {
		pages.put(Person.HR_ROLE, PageName.INDEX_HR_PAGE);
		pages.put(Person.APPLICANT_ROLE, PageName.INDEX_APPLICANT_PAGE);
	}

	public void forwardToIndexByRole(HttpServletRequest request, HttpServletResponse response, String role)
			throws ServletException, IOException {
		String pageName = pages.get(role);
		if (pageName == null || pageName.isEmpty()) {
			pageName = PageName.ERROR_404_PAGE;
		}
		
		response.sendRedirect(pageName);
	}
	
	public static ScreenRoleDispatcher getInstance(){
		return instance;
	}

}
