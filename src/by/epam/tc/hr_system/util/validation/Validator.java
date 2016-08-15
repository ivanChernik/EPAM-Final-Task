package by.epam.tc.hr_system.util.validation;

import org.apache.log4j.Logger;

import by.epam.tc.hr_system.exception.ServiceException;
import by.epam.tc.hr_system.exception.ValidationExeception;
import by.epam.tc.hr_system.util.validation.Validator;

public class Validator {

	private static final Logger log = Logger.getLogger(Validator.class);

	public static void validateString(String property, String propertyName) throws ValidationExeception {

		if (property == null || property.isEmpty()) {
			log.error("Error : String '" + propertyName + "' is null or empty.");
			throw new ValidationExeception("Error : String '" + propertyName + "' is null or empty.");
		}
	}

	public static void validateInt(int number, String methodName, String parameterName) throws ValidationExeception {

	}

	public static int validateAndParseIntToString(String property, String propertyName) throws ValidationExeception {
		validateString(property, propertyName);
		int numer = 0;
		try {
			numer = Integer.parseInt(property);
		} catch (NumberFormatException e) {
			log.error("Error : Variable '" + propertyName + "' has wrong format.");
			throw new ValidationExeception("Error : Variable '" + propertyName + "' has wrong format.");
		}
		return numer;
	}

}
