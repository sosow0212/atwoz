package com.atwoz.member.exception.exceptions.info.option;

public class DrinkInvalidException extends RuntimeException {

    public DrinkInvalidException() {
        super("등록되지 않은 음주 단계입니다.");
    }
}
