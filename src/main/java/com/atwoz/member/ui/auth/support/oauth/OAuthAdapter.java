package com.atwoz.member.ui.auth.support.oauth;

import com.atwoz.member.ui.auth.support.oauth.OAuthProperties.Provider;
import java.util.HashMap;
import java.util.Map;

public class OAuthAdapter {

    private OAuthAdapter() {
    }

    public static Map<String, OAuthProvider> getOauthProviders(final OAuthProperties properties) {
        Map<String, Provider> provider = properties.getProvider();
        System.out.println("aaaa" + provider.keySet());

        Map<String, OAuthProvider> oauthProvider = new HashMap<>();

        properties.getUser().forEach((key, value) -> oauthProvider.put(key,
                new OAuthProvider(value, properties.getProvider().get(key))));
        return oauthProvider;
    }
}
