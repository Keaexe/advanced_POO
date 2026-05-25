package Exceptions;

public class UIException extends Exception {

    private String originalMessage;

    public UIException(String message, String originalMessage) {
        super(message);
        this.originalMessage = originalMessage;
    }

    public String getOriginalMessage() {
        return originalMessage;
    }
}
