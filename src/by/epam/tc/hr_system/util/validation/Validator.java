package by.epam.tc.hr_system.util.validation;

import java.sql.Date;
import java.util.List;

import org.apache.log4j.Logger;

import by.epam.tc.hr_system.command.impl.CreateResumeCommand;
import by.epam.tc.hr_system.exception.ServiceException;
import by.epam.tc.hr_system.exception.validation.IllegalStringLengtnException;
import by.epam.tc.hr_system.exception.validation.ValidationException;
import by.epam.tc.hr_system.exception.validation.IllegalDatesPeriodException;
import by.epam.tc.hr_system.util.validation.Validator;

public class Validator {

	// private static final Logger log = Logger.getLogger(Validator.class);

	public static String validateRequiredString(String property) throws ValidationException {
		property = property.trim();

		if (property == null || property.isEmpty()) {
			throw new ValidationException("Error validation: String is null or empty.");
		}

		return property;
	}
	
	
	public static String validateNotRequiredString(String property) throws ValidationException {
		property = property.trim();

		if (property.length() >= 50) {
			throw new ValidationException("Error validation: String is big.");
		}

		return property;
	}

	public static String validateInputRequiredString(String property) throws ValidationException {

		property = validateRequiredString(property);

		if (property.length() >= 50) {
			throw new IllegalStringLengtnException("Error validation: String is big.");
		}

		return property;
	}

	public static String validateTextAreaRequiredString(String property) throws ValidationException {

		property = validateRequiredString(property);

		if (property.length() >= 800) {
			throw new IllegalStringLengtnException("Error validation: String is big.");
		}

		return property;
	}

	public static void validateInt(int property) throws ValidationException {
		if (property < 0) {
			throw new ValidationException("Error validation: number  < 0.");
		}
	}

	public static void validateDate(Date date) throws ValidationException {
		if (date == null) {
			throw new ValidationException("Error validation: Date is null.");
		}
	}

	public static void validateDatesPeriod(Date eirlyDate, Date laterDate) throws IllegalDatesPeriodException {
		validateDate(eirlyDate);
		validateDate(laterDate);
 
		if (!eirlyDate.after(laterDate)) {
			throw new IllegalDatesPeriodException("Error validation: eirly date after later date");
		}
	}

	public static void validateSelectedItem(List<String> options, String value) {

		if (options != null) {
			if(!options.contains(value)){
				throw new ValidationException("Error searching value in options");
			}
		}

	}

	public static int parseStringToInt(String property) throws ValidationException {
		property = validateInputRequiredString(property);
		int numer = 0;
		try {
			numer = Integer.parseInt(property);
		} catch (NumberFormatException e) {
			throw new ValidationException("Error parsing String to int: Variable has wrong value.");
		}
		return numer;
	}

	public static Date parseStringToDate(String dateString) throws ValidationException {
		dateString = validateInputRequiredString(dateString);
		Date date = null;
		try {
			date = Date.valueOf(dateString);
		} catch (IllegalArgumentException e) {
			// log.error("Error parsing String to Date: Variable has wrong
			// format.");
			throw new ValidationException("Error parsing String to Date: Variable has wrong format.");
		}
		return date;
	}

	public static int[] parseArrayStringToInt(String[] arrayString) throws ValidationException {

		if (arrayString == null) {
			throw new ValidationException("Error validation: arrayString is null.");
		}

		int[] arrrayInt = new int[arrayString.length];

		for (int index = 0; index < arrayString.length; index++) {

			validateRequiredString(arrayString[index]);

			try {
				arrrayInt[index] = Integer.parseInt(arrayString[index]);
			} catch (NumberFormatException e) {
				// log.error("Error parsing array String to int : String has
				// wrong value.");
				throw new ValidationException("Error parsing array String to int : String has wrong value.");
			}
		}
		return arrrayInt;
	}

}
