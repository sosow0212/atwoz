package com.atwoz.member.exception.exceptions.auth;

public class OAuthPlatformNotFountException extends RuntimeException {

    public OAuthPlatformNotFountException() {
        super("소셜 로그인 기관을 찾을 수 없습니다.");
    }
}
