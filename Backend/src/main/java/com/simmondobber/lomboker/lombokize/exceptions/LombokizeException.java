package com.simmondobber.lomboker.lombokize.exceptions;

public class LombokizeException extends RuntimeException {

    public LombokizeException(Throwable cause) {
        super(cause);
    }

    public LombokizeException(String message) {
        super(message);
    }

    public LombokizeException(String message, Throwable cause) {
        super(message, cause);
    }
}
