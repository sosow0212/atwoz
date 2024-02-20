package com.atwoz.member.exception.exceptions.info.profile;

public class ProfileRangeException extends RuntimeException {

    public ProfileRangeException() {
        super("값 범위가 잘못되었습니다.");
    }
}
