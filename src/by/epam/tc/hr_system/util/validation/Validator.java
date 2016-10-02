package by.epam.tc.hr_system.util.validation;

import java.sql.Date;
import java.util.List;

import org.apache.log4j.Logger;

import by.epam.tc.hr_system.command.impl.CreateResumeCommand;
import by.epam.tc.hr_system.exception.ServiceException;
import by.epam.tc.hr_system.exception.validation.IllegalSizeException;
import by.epam.tc.hr_system.exception.validation.NegativeNumberValueException;
import by.epam.tc.hr_system.exception.validation.ValidationException;
import by.epam.tc.hr_system.exception.validation.EmptyPropertyException;
import by.epam.tc.hr_system.exception.validation.IllegalDatesPeriodException;
import by.epam.tc.hr_system.exception.validation.IllegalMinSizeException;
import by.epam.tc.hr_system.exception.validation.IllegalNumberPeriodException;
import by.epam.tc.hr_system.util.validation.Validator;

/**
 * Validate values accordingly rules.
 * 
 * @author Ivan Chernikau
 *
 */
public class Validator {

	/**
	 * Validate on empty String.
	 * 
	 * @param property
	 * @return
	 */
	public static String validateEmptyString(String property) {
		property = property.trim();

		if (property == null || property.isEmpty()) {
			throw new EmptyPropertyException("Error validation: String is null or empty.");
		}

		return property;
	}

	/**
	 * Validate not required String on length.
	 * 
	 * @param property
	 * @param length
	 * @return
	 */

	public static String validateNotRequiredString(String property, int length) {
		property = property.trim();

		if (property.length() > length) {
			throw new IllegalSizeException("Error validation: String is big.");
		}

		return property;
	}

	/**
	 * Validate required String on length and emptiness.
	 * 
	 * @param property
	 * @param length
	 * @return
	 */

	public static String validateRequiredString(String property, int length) {

		property = validateEmptyString(property);

		if (property.length() > length) {
			throw new IllegalSizeException("Error validation: String is big.");
		}

		return property;
	}
	
	/**
	 * 
	 * @param property
	 * @return
	 */
	public static void validateMinSizeString(String property, int length) {

		property = validateEmptyString(property);

		if (property.length() < length) {
			throw new IllegalMinSizeException("Error validation: String is little then requred size.");
		}
	}
	

	/**
	 * Validate on positive int.
	 * 
	 * @param property
	 */

	public static void validatePositiveInt(int property) {
		if (property < 0) {
			throw new NegativeNumberValueException("Error validation: number  < 0.");
		}

	}

	/**
	 * Validate on max value.
	 * 
	 * @param property
	 * @param maxValue
	 */
	public static void validateMaxValueInt(int property, int maxValue) {

		if (property > maxValue) {
			throw new IllegalSizeException("Error validation: number  > max value .");
		}

	}

	/**
	 * Validate the number period.
	 * 
	 * @param lessNumber
	 * @param moreNumber
	 */
	public static void validateNumberPeriod(int lessNumber, int moreNumber) {
		if (lessNumber > moreNumber) {
			throw new IllegalNumberPeriodException("Error validation: less number > more big number.");
		}

	}

	/**
	 * Validate Date on emptiness.
	 * 
	 * @param date
	 */
	public static void validateDate(Date date) {
		if (date == null) {
			throw new EmptyPropertyException("Error validation: Date is null.");
		}
	}

	/**
	 * Validate a period between dates.
	 * 
	 * @param eirlyDate
	 * @param laterDate
	 */
	public static void validateDatesPeriod(Date eirlyDate, Date laterDate) {
		validateDate(eirlyDate);
		validateDate(laterDate);

		if (eirlyDate.after(laterDate)) {
			throw new IllegalDatesPeriodException("Error validation: eirly date after later date");
		}
	}

	/**
	 * Validate a value on affiliation in list.
	 * 
	 * @param options
	 * @param value
	 */
	public static void validateSelectedItem(List<String> options, String value) {
		validateEmptyString(value);
		if (options != null) {
			if (!options.contains(value)) {
				throw new ValidationException("Error searching value in options");
			}
		}

	}

}
