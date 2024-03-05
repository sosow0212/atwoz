package com.atwoz.member.exception.exceptions.info.hobby;

public class HobbyDuplicateException extends RuntimeException {

    public HobbyDuplicateException() {
        super("취미를 중복 선택하면 안 됩니다.");
    }
}
