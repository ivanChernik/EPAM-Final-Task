package by.epam.tc.hr_system.service;

import by.epam.tc.hr_system.service.impl.UserServiceImpl;

public class ServiceFactory {
	private static final ServiceFactory instance = new ServiceFactory();
	private IUserService userService = new UserServiceImpl();
	
	public static ServiceFactory getInstance(){
		return instance;
	}

	public IUserService getUserService(){
		return userService;
	}
	
}
