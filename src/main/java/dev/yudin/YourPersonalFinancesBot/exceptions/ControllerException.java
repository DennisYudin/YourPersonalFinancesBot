package dev.yudin.YourPersonalFinancesBot.exceptions;

public class ControllerException extends RuntimeException {

    public ControllerException() {
        super();
    }

    public ControllerException(String message, Throwable cause) {
        super(message, cause);
    }

    public ControllerException(String message) {
        super(message);
    }
}
