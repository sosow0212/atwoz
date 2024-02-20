package com.atwoz.global.exception.exceptions;

public class NullValueException extends RuntimeException {

    public NullValueException() {
        super("값에 null이 들어가면 안 됩니다.");
    }
}
