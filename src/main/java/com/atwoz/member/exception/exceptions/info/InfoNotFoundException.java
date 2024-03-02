package com.atwoz.member.exception.exceptions.info;

public class InfoNotFoundException extends RuntimeException {

    public InfoNotFoundException() {
        super("회원 정보가 저장되지 않았습니다.");
    }
}
