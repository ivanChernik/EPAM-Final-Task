package by.epam.tc.hr_system.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import by.epam.tc.hr_system.domain.Person;
/**
 * Get person parameter form session.
 * @author Ivan Chernikau
 *
 */
public class AuthorizationUser {

	private static final String PERSON = "person";

	public static Person getPersonInSession(HttpServletRequest request) {
		HttpSession session = request.getSession(true);
		Person person = (Person) session.getAttribute(PERSON);
		return person;
	}
	
	public static void putPersonInSession(HttpServletRequest request, Person person) {
		HttpSession session = request.getSession(true);
		session.setAttribute(PERSON, person);
	}

}
