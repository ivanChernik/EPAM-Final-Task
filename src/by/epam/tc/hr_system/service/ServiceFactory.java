package by.epam.tc.hr_system.service;

import by.epam.tc.hr_system.service.impl.ResumeServiceImpl;
import by.epam.tc.hr_system.service.impl.PersonServiceImpl;
import by.epam.tc.hr_system.service.impl.VacancyResponceServiceImpl;
import by.epam.tc.hr_system.service.impl.VacancyServiceImpl;

public class ServiceFactory {
	private static final ServiceFactory instance = new ServiceFactory();
	private IPersonService userService = new PersonServiceImpl();
	private IVacancyService vacancyService = new VacancyServiceImpl();
	private IResumeService resumeService = new ResumeServiceImpl();
	private IVacancyResponceService vacancyResponceService = new VacancyResponceServiceImpl();

	public static ServiceFactory getInstance() {
		return instance;
	}

	public IPersonService getUserService() {
		return userService;
	}

	public IVacancyService getVacancyService() {
		return vacancyService;
	}

	public IResumeService getResumeService() {
		return resumeService;
	}

	public IVacancyResponceService getVacancyResponceService() {
		return vacancyResponceService;
	}

}
