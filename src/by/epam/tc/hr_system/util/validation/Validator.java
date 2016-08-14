package by.epam.tc.hr_system.util.validation;

import org.apache.log4j.Logger;

import by.epam.tc.hr_system.exception.ServiceException;
import by.epam.tc.hr_system.util.validation.Validator;

public class Validator {

	private static final Logger log = Logger.getLogger(Validator.class);

	public static void validateString(String str, String methodName, String propertyName) throws ServiceException {
		
		methodName.toLowerCase();
		propertyName.toLowerCase();
		
		if (str == null || str.isEmpty()) {
			log.error("Error in method - " + methodName + ": " + propertyName + " .");
			throw new ServiceException("Error in method - " + methodName + ": " + propertyName + " .");
		}
	}

	public static void validateInt(int number, String methodName, String parameterName) throws ServiceException {

	}

}
