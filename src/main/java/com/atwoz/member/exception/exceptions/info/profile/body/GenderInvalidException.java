package com.atwoz.member.exception.exceptions.info.profile.body;

public class GenderInvalidException extends RuntimeException {

    public GenderInvalidException() {
        super("등록되지 않은 성별입니다.");
    }
}
