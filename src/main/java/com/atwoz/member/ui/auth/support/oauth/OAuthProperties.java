package com.atwoz.member.ui.auth.support.oauth;

import java.util.HashMap;
import java.util.Map;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@ConfigurationProperties(prefix = "oauth2")
public class OAuthProperties {

    private final Map<String, Client> user = new HashMap<>();
    private final Map<String, Provider> provider = new HashMap<>();

    @Getter
    @Setter
    public static class Client {
        private String clientId;
        private String redirectUri;
    }

    @Getter
    @Setter
    public static class Provider {
        private String tokenUri;
        private String userInfoUri;
    }
}
