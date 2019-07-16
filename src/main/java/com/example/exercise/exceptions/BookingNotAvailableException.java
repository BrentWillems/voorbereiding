package com.example.exercise.exceptions;

public class BookingNotAvailableException extends RuntimeException {

    public BookingNotAvailableException() {
        super();
    }

    public BookingNotAvailableException(String message) {
        super(message);
    }
}
