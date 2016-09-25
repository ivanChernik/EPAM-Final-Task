package by.epam.tc.hr_system.command.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epam.tc.hr_system.command.ICommand;
import by.epam.tc.hr_system.domain.Vacancy;
import by.epam.tc.hr_system.exception.CommandException;
import by.epam.tc.hr_system.exception.ServiceException;
import by.epam.tc.hr_system.service.IResumeService;
import by.epam.tc.hr_system.service.IVacancyService;
import by.epam.tc.hr_system.service.ServiceFactory;

/**
 * Command shows top (9) most popular vacancies.
 * 
 * @author Ivan Chernikau
 *
 */

public class ShowTopVacancies implements ICommand {


	private static final String COUNT_COMPANIES = "countCompanies";
	private static final String COUNT_VACANCIES = "countVacancies";
	private static final String COUNT_RESUMES = "countResumes";
	private static final String VACANCY_LIST = "vacancyList";


	/**
	 * Invoke IVacancyService to show top (9) vacancies 
	 * 
	 * @param request
	 * @param response
	 * @throws CommandException
	 */
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
		
		ServiceFactory serviceFactory = ServiceFactory.getInstance();

		try {
			IVacancyService vacancyService = serviceFactory.getVacancyService();
			List<Vacancy> vacancyList = vacancyService.getTopVacancies();
			request.setAttribute(VACANCY_LIST, vacancyList);
			request.setAttribute(COUNT_VACANCIES, vacancyService.getCountVacancies());
			request.setAttribute(COUNT_COMPANIES, vacancyService.getCountCompanies());

			IResumeService resumeService = serviceFactory.getResumeService();
			request.setAttribute(COUNT_RESUMES, resumeService.getCountResumes());
		
		} catch (ServiceException e) {
			throw new CommandException(e);
		}

	}

}
