package com.atwoz.member.exception.exceptions.info.profile;

public class JobInvalidException extends RuntimeException {

    public JobInvalidException() {
        super("직업을 찾을 수 없습니다.");
    }
}
