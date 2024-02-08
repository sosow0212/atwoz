package com.atwoz.member.infrastructure.auth;

import com.atwoz.member.domain.auth.OAuthConnectionManager;
import com.atwoz.member.infrastructure.auth.dto.OAuthProviderRequest;

public class OAuthFakeConnectionManager implements OAuthConnectionManager {

    @Override
    public String getAccessTokenResponse(final OAuthProviderRequest oAuthProviderRequest, final String code) {
        return "fakeAccessToken";
    }

    @Override
    public String getMemberInfoResponse(final String accessToken, final String userInfoUrl) {
        return "fakeMemberInfo";
    }
}
