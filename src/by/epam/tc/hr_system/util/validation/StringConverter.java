package by.epam.tc.hr_system.util.validation;

import java.sql.Date;

import by.epam.tc.hr_system.exception.validation.IllegalEntriedValueException;
import by.epam.tc.hr_system.exception.validation.ValidationException;

/**
 * Converts String to others types with validation.
 * 
 * @see Validator
 * @author Ivan Chernikau
 *
 */
public class StringConverter {

	/**
	 * Parse String value to int.
	 * 
	 * @param property
	 * @return int
	 */
	public static int parseStringToInt(String property) {
		property = Validator.validateEmptyString(property);
		int numer = 0;
		try {
			numer = Integer.parseInt(property);
		} catch (NumberFormatException e) {
			throw new IllegalEntriedValueException("Error parsing String to int: Variable has wrong value.");
		}
		Validator.validatePositiveInt(numer);
		return numer;
	}

	/**
	 * Parse String value to Date.
	 * 
	 * @param property
	 * @return Date
	 */
	public static Date parseStringToDate(String dateString) {
		dateString = Validator.validateEmptyString(dateString);
		Date date = null;
		try {
			date = Date.valueOf(dateString);
		} catch (IllegalArgumentException e) {
			// log.error("Error parsing String to Date: Variable has wrong
			// format.");
			throw new IllegalEntriedValueException("Error parsing String to Date: Variable has wrong format.");
		}
		return date;
	}

	/**
	 * Parse array of String values to int.
	 * 
	 * @param property
	 * @return int
	 */
	public static int[] parseArrayStringToInt(String[] arrayString) {

		if (arrayString == null) {
			throw new ValidationException("Error validation: arrayString is null.");
		}

		int[] arrrayInt = new int[arrayString.length];

		for (int index = 0; index < arrayString.length; index++) {

			Validator.validateEmptyString(arrayString[index]);

			try {
				arrrayInt[index] = Integer.parseInt(arrayString[index]);
			} catch (NumberFormatException e) {
				// log.error("Error parsing array String to int : String has
				// wrong value.");
				throw new IllegalEntriedValueException("Error parsing array String to int : String has wrong value.");
			}
		}
		return arrrayInt;
	}

}
