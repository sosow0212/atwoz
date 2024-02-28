package com.atwoz.member.exception.exceptions.info.option;

public class ReligionInvalidException extends RuntimeException {

    public ReligionInvalidException() {
        super("등록되지 않은 종교입니다.");
    }
}
