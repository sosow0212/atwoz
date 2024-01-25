package com.atwoz.member.application.auth;

import com.atwoz.member.ui.auth.support.oauth.OAuthProvider;

public interface OAuthConnectionManager {

    String getAccessToken(final OAuthProvider oAuthProvider, final String code);

    String extractRealInfo(final String accessToken, final String userInfoUrl);
}
