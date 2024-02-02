package com.atwoz.member.ui.auth.support.oauth;

import java.util.Arrays;

public enum OAuthPlatform {
    KAKAO("kakao");

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