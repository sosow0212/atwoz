package com.atwoz.member.application.auth;

import com.atwoz.member.application.auth.dto.LoginRequest;
import com.atwoz.member.domain.auth.TokenProvider;
import com.atwoz.member.domain.auth.UserProfile;
import com.atwoz.member.domain.member.Member;
import com.atwoz.member.domain.member.MemberRepository;
import com.atwoz.member.domain.member.NicknameGenerator;
import com.atwoz.member.ui.auth.dto.OAuthTokenResponse;
import com.atwoz.member.ui.auth.support.oauth.InMemoryProviderRepository;
import com.atwoz.member.ui.auth.support.oauth.OAuthAttributes;
import com.atwoz.member.ui.auth.support.oauth.OAuthProvider;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@RequiredArgsConstructor
@Service
public class AuthService {

    private final MemberRepository memberRepository;
    private final TokenProvider tokenProvider;
    private final NicknameGenerator nicknameGenerator;
    private final InMemoryProviderRepository inMemoryProviderRepository;

    @Transactional
    public String login(final LoginRequest request) {
        OAuthProvider oauthProvider = inMemoryProviderRepository.findByProviderName(request.provider());

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("grant_type", "authorization_code");
        params.add("client_id", oauthProvider.getClientId());
        params.add("redirect_uri", oauthProvider.getRedirectUrl());
        params.add("code", request.code());

        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(params, httpHeaders);
        String response = restTemplate.postForObject(oauthProvider.getTokenUrl(), requestEntity, String.class);
        OAuthTokenResponse oauthTokenResponse = jsonToTokenResponse(response);

        Member member = extractRealInfo(oauthTokenResponse.getAccessToken(), oauthProvider.getUserInfoUrl(),
                request.provider());

        return tokenProvider.create(member.getId());
    }

    public Member extractRealInfo(final String accessToken, final String userInfoUrl, final String provider) {
        try {
            RestTemplate restTemplate = new RestTemplate();
            HttpHeaders headers = new HttpHeaders();
            headers.setBearerAuth(accessToken);
            headers.setContentType(MediaType.APPLICATION_JSON);
            RequestEntity<Void> requestEntity = new RequestEntity<>(headers, HttpMethod.GET, new URI(userInfoUrl));

            ResponseEntity<String> responseEntity = restTemplate.exchange(requestEntity, String.class);
            HashMap<String, Object> map = jsonToMap(responseEntity.getBody());
            UserProfile userProfile = OAuthAttributes.extract(provider, map);

            return memberRepository.findByEmail(userProfile.getEmail())
                    .orElseGet(() -> {
                        return createMember(userProfile);
                    });

        } catch (URISyntaxException e) {
            throw new RuntimeException();
        }
    }

    private Member createMember(final UserProfile userProfile) {
        Member member = userProfile.toMember();
        memberRepository.save(member);
        return member;
    }

    private OAuthTokenResponse jsonToTokenResponse(final String json) {
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            OAuthTokenResponse oauthTokenResponse = objectMapper.readValue(json, OAuthTokenResponse.class);
            return oauthTokenResponse;
        } catch (JsonProcessingException e) {
            throw new RuntimeException();
        }
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
}
