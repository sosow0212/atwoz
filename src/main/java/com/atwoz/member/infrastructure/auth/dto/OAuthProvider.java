package com.atwoz.member.infrastructure.auth.dto;

public record OAuthProvider(
        String clientId,
        String redirectUri,
        String tokenUri,
        String userInfoUri
) {
}
