package org.skypro.exam.exception;

public class RequestedTooLessException extends RuntimeException {
    public RequestedTooLessException() {
        super();
    }

    public RequestedTooLessException(String message) {
        super(message);
    }

    public RequestedTooLessException(long countRequested) {
        super("Too less items requested exception. Requested " + countRequested);
    }
}
