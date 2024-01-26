package com.atwoz.member.application.auth;

import com.atwoz.member.ui.auth.support.oauth.OAuthProperties.OAuthProvider;

public interface OAuthConnectionManager {

    String getAccessTokenResponse (final OAuthProvider oAuthProvider, final String code);

    String getMemberInfoResponse(final String accessToken, final String userInfoUrl);
}
