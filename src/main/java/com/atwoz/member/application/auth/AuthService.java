package com.atwoz.member.application.auth;

import com.atwoz.member.application.auth.dto.LoginRequest;
import com.atwoz.member.application.auth.dto.OAuthTokenResponse;
import com.atwoz.member.config.oauth.OAuthAttributes;
import com.atwoz.member.config.oauth.OAuthProvider;
import com.atwoz.member.config.oauth.OAuthProviderRepository;
import com.atwoz.member.domain.auth.TokenProvider;
import com.atwoz.member.domain.auth.UserProfile;
import com.atwoz.member.domain.member.Member;
import com.atwoz.member.domain.member.MemberRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.HashMap;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class AuthService {

    private final MemberRepository memberRepository;
    private final TokenProvider tokenProvider;
    private final OAuthProviderRepository oAuthProviderRepository;
    private final OAuthConnectionManager oAuthConnectionManager;

    @Transactional
    public String login(final LoginRequest request) {
        OAuthProvider oauthProvider = oAuthProviderRepository.findByProviderName(request.provider());
        String accessTokenResponse = oAuthConnectionManager.getAccessTokenResponse(oauthProvider, request.code());
        OAuthTokenResponse oAuthTokenResponse = getOAuthTokenResponse(accessTokenResponse);
        Member member = getMemberWith(oAuthTokenResponse.getAccessToken(), oauthProvider.getUserInfoUrl(),
                request.provider());

        return tokenProvider.create(member.getId());
    }

    private OAuthTokenResponse getOAuthTokenResponse(final String accessTokenResponse) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.readValue(accessTokenResponse, OAuthTokenResponse.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException();
        }
    }

    private Member getMemberWith(final String accessToken, final String userInfoUrl, final String provider) {
        String response = oAuthConnectionManager.extractRealInfo(accessToken, userInfoUrl);
        HashMap<String, Object> map = jsonToMap(response);
        UserProfile userProfile = OAuthAttributes.extract(provider, map);

        return getOrCreateMemberByProfile(userProfile);
    }

    private HashMap<String, Object> jsonToMap(final String json) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.readValue(json, new TypeReference<HashMap<String, Object>>() {
            });
        } catch (JsonProcessingException e) {
            throw new RuntimeException();
        }
    }

    private Member getOrCreateMemberByProfile(final UserProfile userProfile) {
        return memberRepository.findByEmail(userProfile.getEmail())
                .orElseGet(() -> {
                    return createMemberWith(userProfile);
                });
    }

    private Member createMemberWith(final UserProfile userProfile) {
        Member member = userProfile.toMember();
        memberRepository.save(member);
        return member;
    }
}
