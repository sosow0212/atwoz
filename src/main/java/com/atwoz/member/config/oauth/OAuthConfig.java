package com.atwoz.member.config.oauth;

import java.util.Map;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(OAuthProperties.class)
public class OAuthConfig {

    private final OAuthProperties oauthProperties;

    public OAuthConfig(final OAuthProperties oauthProperties) {
        this.oauthProperties = oauthProperties;
    }

    @Bean
    public OAuthProviderRepository oAuthProviderRepository() {
        Map<String, OAuthProvider> providers = OAuthAdapter.getOauthProviders(oauthProperties);
        return new OAuthProviderRepository(providers);
    }
}