package com.atwoz.member.infrastructure.auth;

import com.atwoz.member.application.auth.OAuthRequester;
import com.atwoz.member.infrastructure.auth.dto.MemberInfoResponse;
import com.atwoz.member.infrastructure.auth.dto.OAuthProviderRequest;

public class OAuthFakeRequester implements OAuthRequester {

    @Override
    public String getAccessToken(final String code, final OAuthProviderRequest provider) {
        return "accessToken";
    }

    @Override
    public MemberInfoResponse getMemberInfo(final String accessToken, final OAuthProviderRequest oAuthProviderRequest) {
        return new MemberInfoResponse("emailKeyWord", "nicknameKeyWord");
    }
}
