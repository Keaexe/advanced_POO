package Exceptions;

public class UIException extends Exception {

    private String message, originalMessage;

    public UIException(String message, String originalMessage) {
        this.message = message;
        this.originalMessage = originalMessage;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public String getOriginalMessage() {
        return originalMessage;
    }
}
