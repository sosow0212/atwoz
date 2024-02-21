package com.atwoz.member.exception.exceptions.info.hobby;

public class HobbyNotFoundException extends RuntimeException {

    public HobbyNotFoundException() {
        super("취미를 찾을 수 없습니다.");
    }
}
