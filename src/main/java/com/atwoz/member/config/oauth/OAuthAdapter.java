package com.atwoz.member.config.oauth;

import com.atwoz.member.config.oauth.OAuthProperties.Provider;
import com.atwoz.member.config.oauth.OAuthProperties.User;
import java.util.HashMap;
import java.util.Map;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class OAuthAdapter {

    public static Map<String, OAuthProvider> getOauthProviders(final OAuthProperties properties) {
        Map<String, OAuthProvider> oauthProvider = new HashMap<>();
        Map<String, User> user = properties.getUser();
        Map<String, Provider> provider = properties.getProvider();

        user.forEach((key, value) ->
                oauthProvider.put(key, createOAuthProvider(user.get(key), provider.get(key))));

        return oauthProvider;
    }

    private static OAuthProvider createOAuthProvider(final User user, final Provider provider) {
        return new OAuthProvider(user, provider);
    }
}
