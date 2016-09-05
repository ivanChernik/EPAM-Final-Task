package by.epam.tc.hr_system.util.validation;

import java.sql.Date;
import java.util.List;

import org.apache.log4j.Logger;

import by.epam.tc.hr_system.command.impl.CreateResumeCommand;
import by.epam.tc.hr_system.exception.ServiceException;
import by.epam.tc.hr_system.exception.validation.IllegalStringLengtnException;
import by.epam.tc.hr_system.exception.validation.NegativeNumberValueException;
import by.epam.tc.hr_system.exception.validation.ValidationException;
import by.epam.tc.hr_system.exception.validation.EmptyPropertyException;
import by.epam.tc.hr_system.exception.validation.IllegalDatesPeriodException;
import by.epam.tc.hr_system.util.validation.Validator;

public class Validator {

	// private static final Logger log = Logger.getLogger(Validator.class);

	public static String validateEmptyString(String property) {
		property = property.trim();

		if (property == null || property.isEmpty()) {
			throw new EmptyPropertyException("Error validation: String is null or empty.");
		}

		return property;
	}

	public static String validateNotRequiredString(String property, int length) {
		property = property.trim();

		if (property.length() >= length) {
			throw new IllegalStringLengtnException("Error validation: String is big.");
		}

		return property;
	}

	public static String validateRequiredString(String property, int length) {

		property = validateEmptyString(property);

		if (property.length() >= length) {
			throw new IllegalStringLengtnException("Error validation: String is big.");
		}

		return property;
	}

	public static void validatePositiveInt(int property) {
		if (property < 0) {
			throw new NegativeNumberValueException("Error validation: number  < 0.");
		}
	}

	public static void validateDate(Date date) throws ValidationException {
		if (date == null) {
			throw new EmptyPropertyException("Error validation: Date is null.");
		}
	}

	public static void validateDatesPeriod(Date eirlyDate, Date laterDate) {
		validateDate(eirlyDate);
		validateDate(laterDate);

		if (eirlyDate.after(laterDate)) {
			throw new IllegalDatesPeriodException("Error validation: eirly date after later date");
		}
	}

	public static void validateSelectedItem(List<String> options, String value) {
		validateEmptyString(value);
		if (options != null) {
			if (!options.contains(value)) {
				throw new ValidationException("Error searching value in options");
			}
		}

	}

}
