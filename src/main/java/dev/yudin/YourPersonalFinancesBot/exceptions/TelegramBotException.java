package dev.yudin.YourPersonalFinancesBot.exceptions;

public class TelegramBotException extends RuntimeException {

    public TelegramBotException() {
        super();
    }

    public TelegramBotException(String message, Throwable cause) {
        super(message, cause);
    }

    public TelegramBotException(String message) {
        super(message);
    }
}
