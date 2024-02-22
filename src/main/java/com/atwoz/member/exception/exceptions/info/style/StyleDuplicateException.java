package com.atwoz.member.exception.exceptions.info.style;

public class StyleDuplicateException extends RuntimeException {

    public StyleDuplicateException() {
        super("스타일을 중복 선택하면 안 됩니다.");
    }
}
