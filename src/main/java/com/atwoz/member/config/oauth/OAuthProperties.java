package com.atwoz.member.config.oauth;

import java.util.HashMap;
import java.util.Map;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@ConfigurationProperties(prefix = "oauth2")
public class OAuthProperties {

    private final Map<String, User> user = new HashMap<>();
    private final Map<String, Provider> provider = new HashMap<>();

    @Getter
    @Setter
    public static class User {

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
