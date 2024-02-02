package com.atwoz.member.ui.auth.support.auth;

import java.util.Arrays;

public enum OAuthPlatform {
    KAKAO("KAKAO");

    private final String name;

    OAuthPlatform(final String name) {
        this.name = name;
    }

    public static OAuthPlatform findPlatform(final String name) {
        return Arrays.stream(values())
                .filter(platform -> name.equals(platform.name))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}
