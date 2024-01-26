package com.atwoz.member.ui.auth.support.oauth;

import java.util.HashMap;
import java.util.Map;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@ConfigurationProperties(prefix = "oauth2")
public class OAuthProperties {

    Map<String, OAuthProvider> providers = new HashMap<>();

    @Getter
    @Setter
    public static class OAuthProvider {

        private String clientId;
        private String redirectUri;
        private String tokenUri;
        private String userInfoUri;
    }

    public OAuthProvider findByProviderName(final String name) {
        return providers.get(name);
    }
}
