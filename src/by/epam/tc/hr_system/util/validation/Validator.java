package by.epam.tc.hr_system.util.validation;

import org.apache.log4j.Logger;

import by.epam.tc.hr_system.exception.ServiceException;
import by.epam.tc.hr_system.exception.ValidationExeception;
import by.epam.tc.hr_system.util.validation.Validator;

public class Validator {

	private static final Logger log = Logger.getLogger(Validator.class);

	public static void validateString(String property, String propertyName) throws ValidationExeception {

		if (property == null || property.isEmpty()) {
			log.error("Error validation: String '" + propertyName + "' is null or empty.");
			throw new ValidationExeception("Error validation: String '" + propertyName + "' is null or empty.");
		}
	}

	public static void validateInt(int property, String propertyName) throws ValidationExeception {
		if(property < 0){
			log.error("Error validation: number '" + propertyName + "' < 0.");
			throw new ValidationExeception("Error validation: number '" + propertyName + "' < 0.");
		}
	}

	public static int parseStringToInt(String property, String propertyName) throws ValidationExeception {
		validateString(property, propertyName);
		int numer = 0;
		try {
			numer = Integer.parseInt(property);
		} catch (NumberFormatException e) {
			log.error("Error parsing String to int: Variable '" + propertyName + "' has wrong value.");
			throw new ValidationExeception(
					"Error parsing String to int: Variable '" + propertyName + "' has wrong value.");
		}
		return numer;
	}

	public static int[] parseArrayStringToInt(String[] arrayString) throws ValidationExeception {

		if(arrayString == null){
			log.error("Error validation: arrayString is null.");
			throw new ValidationExeception("Error validation: arrayString is null.");
		}
		
		int[] arrrayInt = new int[arrayString.length];

		for (int index = 0; index < arrayString.length; index++) {

			validateString(arrayString[index], "");

			try {
				arrrayInt[index] = Integer.parseInt(arrayString[index]);
			} catch (NumberFormatException e) {
				log.error("Error parsing array String to int : String has wrong value.");
				throw new ValidationExeception("Error parsing array String to int : String has wrong value.");
			}
		}
		return arrrayInt;
	}

}
