package com.atwoz.member.exception.exceptions.info.profile;

public class ProfileNotFoundException extends RuntimeException {

    public ProfileNotFoundException() {
        super("Profile이 저장되지 않았습니다.");
    }
}
