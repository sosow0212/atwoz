package com.atwoz.member.exception.exceptions.info.profile.position;

public class LatitudeRangeException extends RuntimeException {

    public LatitudeRangeException() {
        super("위도 범위가 올바르지 않습니다.");
    }
}
