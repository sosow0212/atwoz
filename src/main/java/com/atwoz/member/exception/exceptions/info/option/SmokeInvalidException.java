package com.atwoz.member.exception.exceptions.info.option;

public class SmokeInvalidException extends RuntimeException {

    public SmokeInvalidException() {
        super("등록되지 않은 흡연 단계입니다.");
    }
}
