package ru.skypro.coursework.courseworkeasyauction.exeption;


public class NotIdException extends RuntimeException{
    public NotIdException(String message) {
        super(message);
    }

    public NotIdException() {
        super();
    }
}
