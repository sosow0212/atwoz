package com.atwoz.member.ui.auth.support.oauth;

import java.util.HashMap;
import java.util.Map;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class OAuthAdapter {

    public static Map<String, OAuthProvider> getOauthProviders(final OAuthProperties properties) {
        Map<String, OAuthProvider> oauthProvider = new HashMap<>();

        properties.getUser().forEach((key, value) -> oauthProvider.put(key,
                new OAuthProvider(value, properties.getProvider().get(key))));
        return oauthProvider;
    }
}
