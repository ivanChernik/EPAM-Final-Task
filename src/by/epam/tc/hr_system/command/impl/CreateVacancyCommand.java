package by.epam.tc.hr_system.command.impl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import by.epam.tc.hr_system.command.ICommand;
import by.epam.tc.hr_system.domain.Person;
import by.epam.tc.hr_system.exception.ServiceException;
import by.epam.tc.hr_system.service.IVacancyService;
import by.epam.tc.hr_system.service.ServiceFactory;
import by.epam.tc.hr_system.util.PageName;
import by.epam.tc.hr_system.util.parameter.VacancyParameter;

public class CreateVacancyCommand implements ICommand {

	private static final String PERSON = "person";
	private static final Logger log = Logger.getLogger(CreateVacancyCommand.class);

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {

		try {
			HttpSession session = request.getSession(true);

			Person person = (Person) session.getAttribute(PERSON);

			if (person == null) {

				request.getRequestDispatcher(PageName.INDEX_PAGE).forward(request, response);

				return;
			}

			String name = request.getParameter(VacancyParameter.TITLE_VACANCY);
			String descrption = request.getParameter(VacancyParameter.DESCRIPTION);
			String shortDescription = request.getParameter(VacancyParameter.SHORT_DESCRIPTION);
			String requirement = request.getParameter(VacancyParameter.REQUIREMENT);
			String salary = request.getParameter(VacancyParameter.SALARY);
			String companyName = request.getParameter(VacancyParameter.COMPANY_NAME);
			String contactInformation = request.getParameter(VacancyParameter.CONTACT_DATA);
			String employment = request.getParameter(VacancyParameter.EMPLOYMENT);

			ServiceFactory serviceFactory = ServiceFactory.getInstance();

			try {
				IVacancyService vacancyService = serviceFactory.getVacancyService();
				vacancyService.addVacancy(name, descrption, shortDescription, requirement, salary, companyName,
						contactInformation, employment, 5);
				request.getRequestDispatcher(PageName.INDEX_HR_PAGE).forward(request, response);
				return;
			} catch (ServiceException e) {
				request.getRequestDispatcher(PageName.CREATION_VACANCY_PAGE).forward(request, response);
				return;
			}
			
			
		} catch (ServletException e) {
			log.error(e);
		} catch (IOException e) {
			log.error(e);
		}

	}

}
