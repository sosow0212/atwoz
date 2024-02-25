package com.atwoz.member.exception.exceptions.info.profile;

public class JobNotFoundException extends RuntimeException {

    public JobNotFoundException() {
        super("직업을 찾을 수 없습니다.");
    }
}
