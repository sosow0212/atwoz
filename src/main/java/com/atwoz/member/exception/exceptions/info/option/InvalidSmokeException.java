package com.atwoz.member.exception.exceptions.info.option;

public class InvalidSmokeException extends RuntimeException {

    public InvalidSmokeException() {
        super("등록되지 않은 흡연 단계입니다.");
    }
}
