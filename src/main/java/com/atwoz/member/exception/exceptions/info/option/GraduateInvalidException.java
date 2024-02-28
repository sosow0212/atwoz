package com.atwoz.member.exception.exceptions.info.option;

public class GraduateInvalidException extends RuntimeException {

    public GraduateInvalidException() {
        super("등록되지 않은 최종 학력입니다.");
    }
}
