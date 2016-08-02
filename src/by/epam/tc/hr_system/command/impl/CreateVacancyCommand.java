package by.epam.tc.hr_system.command.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.epam.tc.hr_system.command.ICommand;
import by.epam.tc.hr_system.exception.ServiceException;
import by.epam.tc.hr_system.service.IVacancyService;
import by.epam.tc.hr_system.service.ServiceFactory;
import by.epam.tc.hr_system.util.parameter.VacancyParameter;

public class CreateVacancyCommand implements ICommand {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession(false);

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
		} catch (ServiceException e) {
			
		}

	}

}
