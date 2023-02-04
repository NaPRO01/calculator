package com.kata.test;

public class InputExceptions extends Exception{

    public InputExceptions() {
    }

    public InputExceptions(String message) {
        super(message);
    }

    public InputExceptions(String message, Throwable cause) {
        super(message, cause);
    }

    public InputExceptions(Throwable cause) {
        super(cause);
    }

    public InputExceptions(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
