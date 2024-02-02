package com.atwoz.member.domain.auth;

import com.atwoz.member.infrastructure.auth.dto.OAuthProvider;

public interface OAuthConnectionManager {

    String getAccessTokenResponse (final OAuthProvider oAuthProvider, final String code);

    String getMemberInfoResponse(final String accessToken, final String userInfoUrl);
}
