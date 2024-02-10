package com.atwoz.member.application.auth;

import com.atwoz.member.infrastructure.auth.dto.MemberInfoResponse;
import com.atwoz.member.infrastructure.auth.dto.OAuthProviderRequest;

public interface OAuthRequester {

    String getAccessToken(final String code, final OAuthProviderRequest provider);

    MemberInfoResponse getMemberInfo(final String accessToken, final OAuthProviderRequest oAuthProviderRequest);
}
