package Utils;

import Exception.ValidationException;

public final class StringUtils {

    public static String requireNotBlank(String value, String fieldName) {
        if (value == null || value.isBlank()) {
            throw new ValidationException(fieldName);
        }
        return value.strip();
    }

    public static String requireNotBlankNullable(String value, String fieldName){
        if(value == null){
            return null;
        }else{
            return requireNotBlank(value, fieldName);
        }
    }
}
