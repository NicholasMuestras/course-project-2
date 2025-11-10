package org.skypro.exam.exception;

public class RequestedTooMuchException extends RuntimeException {
    public RequestedTooMuchException() {
        super();
    }

    public RequestedTooMuchException(String message) {
        super(message);
    }

    public RequestedTooMuchException(long countAvailable, long countRequested) {
        super("Too many items requested exception. Available " + countAvailable + " Requested " + countRequested);
    }
}
