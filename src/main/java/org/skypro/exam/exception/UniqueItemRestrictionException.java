package org.skypro.exam.exception;

public class UniqueItemRestrictionException extends RuntimeException {
    public UniqueItemRestrictionException() {
        super();
    }

    public UniqueItemRestrictionException(String message) {
        super(message);
    }
}
