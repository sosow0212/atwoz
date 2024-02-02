package com.atwoz.member.ui.auth.support.auth;

import com.atwoz.member.infrastructure.auth.dto.OAuthProvider;
import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@AllArgsConstructor
@ConfigurationProperties(prefix = "oauth2")
public class OAuthProperties {

    private final Map<OAuthPlatform, OAuthProvider> properties;

    public OAuthProvider findByProviderName(final String name) {
        return properties.get(OAuthPlatform.findPlatform(name));
    }
}
