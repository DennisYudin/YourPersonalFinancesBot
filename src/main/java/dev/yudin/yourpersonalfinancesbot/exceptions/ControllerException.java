package dev.yudin.yourpersonalfinancesbot.exceptions;

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
