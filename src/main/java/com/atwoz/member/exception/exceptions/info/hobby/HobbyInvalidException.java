package com.atwoz.member.exception.exceptions.info.hobby;

public class HobbyInvalidException extends RuntimeException {

    public HobbyInvalidException() {
        super("등록되지 않은 취미입니다.");
    }
}
