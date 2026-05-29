package Utils;

import Exceptions.ValidationException;

import java.time.LocalDate;

public final class ValidationUtils {
    static public final Integer MIN_AGE_REQUIRED = 18;
    /**
     * Matches a non-empty string containing only Unicode letters,
     * Unicode digits, single quotes and spaces.
     * Get a try : <a href="https://www.freeformatter.com/java-regex-tester.html">...</a>
     */
    public static final String ALPHANUMERIC_REGEX = "^[\\p{L}\\p{N} '\\-]+$";
    public static final String ALPHANUMERIC_ERROR_MESSAGE = "No special character allowed.";

    /**
     * Matches an HTTPS URL with a domain name and an extension.
     * Allows an optional path after the extension.
     * Get a try : <a href="https://www.freeformatter.com/java-regex-tester.html">...</a>
     */
    public static final String URL_REGEX = "^https://[\\p{L}\\p{N}.-]+\\.[\\p{L}]{2,}.*$";
    public static final String URL_ERROR_MESSAGE = "Website URL must start with 'https://' then contain a domain name such as '.com'";

    public static String validateString(
            String value,
            String fieldName,
            boolean isRequired,
            Integer maxLength,
            String regex,
            String regexErrorMessage
    ) throws ValidationException {
        if (value == null || value.isBlank()) {
            if (isRequired) {
                throw new ValidationException(fieldName + " is mandatory.");
            }
            return null;
        }

        String cleanedValue = value.strip();

        if (maxLength != null && cleanedValue.length() > maxLength) {
            throw new ValidationException(
                    fieldName + " maximum number of characters = "
                            + maxLength
                            + " characters."
            );
        }

        if (regex != null && !cleanedValue.matches(regex)) {
            if (regexErrorMessage != null && !regexErrorMessage.isBlank()) {
                throw new ValidationException(fieldName + " - " + regexErrorMessage);
            }

            throw new ValidationException(fieldName + " has an invalid format.");
        }

        return cleanedValue;
    }

    public static String validateString(
            String value,
            String fieldName,
            boolean isRequired,
            Integer maxLength
    ) throws ValidationException {
        return validateString(value, fieldName, isRequired, maxLength, null, null);
    }

    public static String validateString(String value, String fieldName, boolean isRequired)
            throws ValidationException {
        return validateString(value, fieldName, isRequired, Integer.MAX_VALUE, null, null);
    }

    public static Integer validateInteger(Integer value, String fieldName, boolean isRequired, Integer min, Integer max)
            throws ValidationException {
        return validateNumber(value, fieldName, isRequired, min, max);
    }

    public static Double validateDouble(Double value, String fieldName, boolean isRequired, Double min, Double max)
            throws ValidationException {
        return validateNumber(value, fieldName, isRequired, min, max);
    }

    public static LocalDate validateDate(LocalDate value, String fieldName, boolean isRequired, boolean checkMinimumAge,
                                         boolean allowPast, boolean allowFuture) throws ValidationException {

        if (value == null) {
            if (isRequired) {
                throw new ValidationException(fieldName + " is required.");
            }
            return null;
        }

        LocalDate today = LocalDate.now();

        if (checkMinimumAge) {
            LocalDate latestAllowedBirthDate = today.minusYears(MIN_AGE_REQUIRED);

            if (value.isAfter(latestAllowedBirthDate)) {
                throw new ValidationException("Age must be at least " + MIN_AGE_REQUIRED + " years old.");
            }
        } else {
            if (!allowPast && value.isBefore(today)) {
                throw new ValidationException(fieldName + " cannot be in the past.");
            }

            if (!allowFuture && value.isAfter(today)) {
                throw new ValidationException(fieldName + " cannot be in the future.");
            }
        }

        return value;
    }

    private static <NumericType extends Number & Comparable<NumericType>> NumericType validateNumber(
            NumericType value, String fieldName, boolean isRequired, NumericType min, NumericType max)
            throws ValidationException {
        if (value == null) {
            if (isRequired) {
                throw new ValidationException(fieldName);
            }
            return null;
        }

        if (min != null && value.compareTo(min) < 0) {
            throw new ValidationException(fieldName);
        }

        if (max != null && value.compareTo(max) > 0) {
            throw new ValidationException(fieldName);
        }

        return value;
    }


}
