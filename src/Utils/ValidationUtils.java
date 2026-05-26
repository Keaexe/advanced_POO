package Utils;

import Exceptions.ValidationException;

import java.time.LocalDate;

public final class ValidationUtils {
    static public final Integer MIN_AGE_REQUIRED = 18;

    /**
     * Validate and clean a string according to the arguments.
     * @param value  The string to check.
     * @param fieldName  The field name that will be display in errors.
     * @param isRequired True if the field is mandatory.
     * @param maxLength - Optional: The maximum number of character according to database or other limitations.
     * @return the cleaned value, or null, or throw a ValidationException.
     */
    public static String validateString(String value, String fieldName, boolean isRequired, Integer maxLength) {
        if (value == null || value.isBlank()) {
            if (isRequired) {
                throw new ValidationException(fieldName + " is mandatory.");
            }
            return null;
        }

        String cleanedValue = value.strip();

        if (maxLength != null && cleanedValue.length() > maxLength) {
            throw new ValidationException(fieldName + " maiximum number of characters = " + maxLength + " caractères.");
        }

        return cleanedValue;
    }

    public static String validateString(String value, String fieldName, boolean isRequired){
        return validateString(value, fieldName, isRequired, Integer.MAX_VALUE );
    }

    /**
     * Same as validateString but for Integer
     */
    public static Integer validateInteger(Integer value, String fieldName, boolean isRequired, Integer min, Integer max) {
        return validateNumber(value, fieldName, isRequired, min, max);
    }


    /**
     * Same as validateString but for Double
     */
    public static Double validateDouble(Double value, String fieldName, boolean isRequired, Double min, Double max) {
        return validateNumber(value, fieldName, isRequired, min, max);
    }

    private static <NumericType extends Number & Comparable<NumericType>> NumericType validateNumber(NumericType value, String fieldName, boolean isRequired, NumericType min, NumericType max) {
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

    public static LocalDate validateDate(LocalDate value, String fieldName, boolean isRequired,boolean checkMinimumAge, boolean allowPast, boolean allowFuture) {

        if (value == null) {
            if (isRequired) {
                throw new ValidationException(fieldName + " is required.");
            }
            return null;
        }

        LocalDate today = LocalDate.now();

        if(checkMinimumAge){
            LocalDate latestAllowedBirthDate = today.minusYears(MIN_AGE_REQUIRED);

            if (value.isAfter(latestAllowedBirthDate)) {
                throw new ValidationException("You must be at least " + MIN_AGE_REQUIRED + " years old.");
            }
        } else{
            if (!allowPast && value.isBefore(today)) {
                throw new ValidationException(fieldName + " cannot be in the past.");
            }

            if (!allowFuture && value.isAfter(today)) {
                throw new ValidationException(fieldName + " cannot be in the future.");
            }
        }

        return value;
    }
}
