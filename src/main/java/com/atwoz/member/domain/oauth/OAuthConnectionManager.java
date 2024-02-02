package com.atwoz.member.domain.oauth;

import com.atwoz.member.ui.auth.support.oauth.OAuthProvider;

public interface OAuthConnectionManager {

    String getAccessTokenResponse (final OAuthProvider oAuthProvider, final String code);

    String getMemberInfoResponse(final String accessToken, final String userInfoUrl);
}
