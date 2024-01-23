package com.atwoz.member.ui.auth.support.oauth;

import com.atwoz.member.domain.auth.UserProfile;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Arrays;
import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

public enum OAuthAttributes {

    KAKAO("kakao") {
        @Override
        public UserProfile of(final Map<String, Object> attributes) {
            ObjectMapper objectMapper = new ObjectMapper();
            KakaoProperties kakaoProperties = objectMapper.convertValue(attributes.get("properties"),
                    KakaoProperties.class);
            KakaoProfile kakaoProfile = objectMapper.convertValue(attributes.get("kakao_account"), KakaoProfile.class);

            return UserProfile.builder()
                    .oauthId(String.valueOf(attributes.get("id")))
                    .email(String.valueOf(kakaoProfile.getEmail()))
                    .name(kakaoProperties.getNickname())
                    .build();
        }

        @Getter
        @JsonIgnoreProperties(ignoreUnknown = true)
        @NoArgsConstructor
        @AllArgsConstructor
        static class KakaoProperties {
            private String nickname;
        }
        @Getter
        @JsonIgnoreProperties(ignoreUnknown = true)
        @NoArgsConstructor
        @AllArgsConstructor
        static class KakaoProfile {
            private String email;
        }
    };

    private final String providerName;

    OAuthAttributes(final String name) {
        this.providerName = name;
    }

    public static UserProfile extract(final String providerName, final Map<String, Object> attributes) {
        OAuthAttributes oauthAttributes = Arrays.stream(values())
                .filter(provider -> providerName.equals(provider.providerName))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);

        return oauthAttributes.of(attributes);
    }

    public abstract UserProfile of(Map<String, Object> attributes);
}
