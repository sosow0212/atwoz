package com.atwoz.member.exception.exceptions.info.style;

public class StyleInvalidException extends RuntimeException {

    public StyleInvalidException() {
        super("등록되지 않은 스타일입니다.");
    }
}
