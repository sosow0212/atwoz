package com.atwoz.member.exception.exceptions.info.option;

public class InvalidMbtiException extends RuntimeException {

    public InvalidMbtiException() {
        super("등록되지 않은 MBTI입니다.");
    }
}
