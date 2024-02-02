package com.atwoz.member.ui.auth.support.oauth;

public record OAuthProvider(
        String clientId,
        String redirectUri,
        String tokenUri,
        String userInfoUri
) {
}
