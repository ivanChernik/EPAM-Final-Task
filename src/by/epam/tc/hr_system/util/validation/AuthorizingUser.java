package by.epam.tc.hr_system.util.validation;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import by.epam.tc.hr_system.domain.Person;
/**
 * Get person parameter form session.
 * @author Ivan Chernikau
 *
 */
public class AuthorizingUser {

	private static final String PERSON = "person";

	public static Person getPersonInSession(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		Person person = (Person) session.getAttribute(PERSON);
		return person;
	}

}
