package com.atwoz.member.ui.auth.support.oauth;

import java.util.HashMap;
import java.util.Map;

public class OAuthProviderRepository {

    private final Map<String, OAuthProvider> providers;

    public OAuthProviderRepository(final Map<String, OAuthProvider> providers) {
        this.providers = new HashMap<>(providers);
    }

    public OAuthProvider findByProviderName(final String name) {
        return providers.get(name);
    }
}
