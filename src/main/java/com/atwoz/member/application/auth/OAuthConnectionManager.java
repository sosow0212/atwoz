package com.atwoz.member.application.auth;

import com.atwoz.member.config.oauth.OAuthProvider;

public interface OAuthConnectionManager {

    String getAccessTokenResponse(final OAuthProvider oAuthProvider, final String code);

    String extractRealInfo(final String accessToken, final String userInfoUrl);
}
