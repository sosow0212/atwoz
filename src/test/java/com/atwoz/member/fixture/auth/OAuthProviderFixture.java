package com.atwoz.member.fixture.auth;

import com.atwoz.member.infrastructure.auth.dto.OAuthProvider;

public class OAuthProviderFixture {

    public static OAuthProvider 인증_기관_생성() {
        return new OAuthProvider("clientId",
                "redirectUri",
                "tokenUri",
                "userInfoUri");
    }
}
