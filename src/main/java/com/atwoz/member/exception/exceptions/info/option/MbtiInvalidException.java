package com.atwoz.member.exception.exceptions.info.option;

public class MbtiInvalidException extends RuntimeException {

    public MbtiInvalidException() {
        super("등록되지 않은 MBTI입니다.");
    }
}
