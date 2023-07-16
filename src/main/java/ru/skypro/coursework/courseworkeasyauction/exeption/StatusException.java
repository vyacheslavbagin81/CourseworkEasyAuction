package ru.skypro.coursework.courseworkeasyauction.exeption;


public class StatusException extends RuntimeException{
    public StatusException(String message) {
        super(message);
    }

    public StatusException() {
        super();
    }
}
