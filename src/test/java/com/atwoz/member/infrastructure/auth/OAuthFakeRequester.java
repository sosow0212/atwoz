package com.atwoz.member.infrastructure.auth;

import com.atwoz.member.application.auth.dto.MemberInfo;
import com.atwoz.member.application.auth.OAuthRequester;
import com.atwoz.member.infrastructure.auth.dto.OAuthProvider;

public class OAuthFakeRequester implements OAuthRequester {

    @Override
    public String getAccessToken(final String code, final OAuthProvider provider) {
        return "accessToken";
    }

    @Override
    public MemberInfo getMemberInfo(final String accessToken, final OAuthProvider oAuthProvider) {
        return new MemberInfo("fake@email.com", "fakeNickname");
    }
}
