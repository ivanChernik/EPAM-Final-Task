package by.epam.tc.hr_system.util.validation;

import java.sql.Date;

import org.apache.log4j.Logger;

import by.epam.tc.hr_system.command.impl.CreateResumeCommand;
import by.epam.tc.hr_system.exception.ServiceException;
import by.epam.tc.hr_system.exception.validation.ValidationExeception;
import by.epam.tc.hr_system.exception.validation.WrongFromToDatesException;
import by.epam.tc.hr_system.util.validation.Validator;

public class Validator {

	//private static final Logger log = Logger.getLogger(Validator.class);

	public static void validateString(String property) throws ValidationExeception {

		if (property == null || property.isEmpty()) {
			throw new ValidationExeception("Error validation: String is null or empty.");
		}
	}

	public static void validateInt(int property) throws ValidationExeception {
		if (property < 0) {
			throw new ValidationExeception("Error validation: number  < 0.");
		}
	}

	public static void validateDate(Date date) throws ValidationExeception {
		if (date == null) {
			throw new ValidationExeception("Error validation: Date is null.");
		}
	}

	public static void validateDates(Date eirlyDate, Date laterDate) throws WrongFromToDatesException {
		validateDate(eirlyDate);
		validateDate(laterDate);

		if (laterDate.before(eirlyDate)){
			throw new WrongFromToDatesException("Error validation: eirly date after later date");
		}
	}

	public static int parseStringToInt(String property) throws ValidationExeception {
		validateString(property);
		int numer = 0;
		try {
			numer = Integer.parseInt(property);
		} catch (NumberFormatException e) {
			throw new ValidationExeception("Error parsing String to int: Variable has wrong value.");
		}
		return numer;
	}

	public static Date parseStringToDate(String dateString) throws ValidationExeception {
		validateString(dateString);
		Date date = null;
		try {
			date = Date.valueOf(dateString);
		} catch (IllegalArgumentException e) {
			//log.error("Error parsing String to Date: Variable has wrong format.");
			throw new ValidationExeception("Error parsing String to Date: Variable has wrong format.");
		}
		return date;
	}

	public static int[] parseArrayStringToInt(String[] arrayString) throws ValidationExeception {

		if (arrayString == null) {
			//log.error("Error validation: arrayString is null.");
			throw new ValidationExeception("Error validation: arrayString is null.");
		}

		int[] arrrayInt = new int[arrayString.length];

		for (int index = 0; index < arrayString.length; index++) {

			validateString(arrayString[index]);

			try {
				arrrayInt[index] = Integer.parseInt(arrayString[index]);
			} catch (NumberFormatException e) {
				//log.error("Error parsing array String to int : String has wrong value.");
				throw new ValidationExeception("Error parsing array String to int : String has wrong value.");
			}
		}
		return arrrayInt;
	}

}
