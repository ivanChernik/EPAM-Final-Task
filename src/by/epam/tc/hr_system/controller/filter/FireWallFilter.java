package by.epam.tc.hr_system.controller.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.epam.tc.hr_system.domain.Person;
import by.epam.tc.hr_system.util.PageName;

@WebFilter(urlPatterns = "/*")
public class FireWallFilter implements Filter {
	private static final String JSP_EXSTENTION = ".jsp";
	private static final String PERSON = "person";

	@Override
	public void destroy() {

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain)
			throws IOException, ServletException {

		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponce = (HttpServletResponse) response;
		String uri = httpRequest.getRequestURI();
		HttpSession session = httpRequest.getSession();

		if (uri != null ) {

			Person person = (Person) session.getAttribute(PERSON);

			if (person != null) {
				checkAccessByRole(httpRequest, httpResponce, person.getRole());
			} else {
				checkNotAuthorizeUserAccess(httpRequest, httpResponce);
			}
		}
		filterChain.doFilter(request, response);
	}

	private void checkAccessByRole(HttpServletRequest request, HttpServletResponse response, String role) {
		switch (role) {
		case Person.APPLICANT_ROLE:
			checkApplicantAccess(request, response);
			break;
		case Person.HR_ROLE:
			checkHRAccess(request, response);
			break;

		default:
			goTo404Page(request, response);
		}
	}

	private void checkApplicantAccess(HttpServletRequest request, HttpServletResponse response) {
		String uri = request.getRequestURI();
		if (uri.contains(PageName.CREATE_VACANCY_PAGE) || uri.contains(PageName.INDEX_HR_PAGE)
				|| uri.contains(PageName.TABLE_VACANCY_PAGE) || uri.contains(PageName.UPDATE_VACANCY_PAGE)
				|| uri.contains(PageName.VACANCY_RESPONCE_PAGE)) {
			goTo404Page(request, response);
		}
	}

	private void checkHRAccess(HttpServletRequest request, HttpServletResponse response) {
		String uri = request.getRequestURI();
		if (uri.contains(PageName.ADDICTION_EDUCATION_PAGE) || uri.contains(PageName.ADDICTION_PREVIOUS_POSITION_PAGE)
				|| uri.contains(PageName.CREATE_RESUME_PAGE) || uri.contains(PageName.INDEX_APPLICANT_PAGE)) {
			goTo404Page(request, response);
		}
	}

	private void checkNotAuthorizeUserAccess(HttpServletRequest request, HttpServletResponse response) {
		String uri = request.getRequestURI();
		if (uri.contains(JSP_EXSTENTION)) {
			if (!uri.contains(PageName.SEARCH_VACANCY_PAGE) && !uri.contains(PageName.SEARCH_RESUME_PAGE)
					&& !uri.contains(PageName.INDEX_PAGE) && !uri.contains(PageName.ERROR_505_PAGE)
					&& !uri.contains(PageName.SEARCH_VACANCY_PAGE) && !uri.contains(PageName.SEARCH_VACANCY_PAGE)
					&& !uri.contains(PageName.RESUME_PAGE)) {
				goTo404Page(request, response);
			}
		}

	}

	private void goTo404Page(HttpServletRequest request, HttpServletResponse response) {
		try {
			response.sendRedirect(PageName.ERROR_404_PAGE);
		} catch (IOException e) {
			throw new RuntimeException("Error going to 404 page", e);
		}
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {

	}

}
