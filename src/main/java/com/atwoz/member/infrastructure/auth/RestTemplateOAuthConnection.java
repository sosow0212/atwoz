package com.atwoz.member.infrastructure.auth;

import com.atwoz.member.domain.auth.OAuthConnectionManager;
import com.atwoz.member.domain.auth.RestTemplateConfig;
import com.atwoz.member.infrastructure.auth.dto.OAuthProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Component
@RequiredArgsConstructor
public class RestTemplateOAuthConnection implements OAuthConnectionManager {

    private final RestTemplateConfig restTemplateConfig;

    @Override
    public String getAccessTokenResponse(final OAuthProvider oAuthProvider, final String code) {
        RestTemplate restTemplate = restTemplateConfig.restTemplate();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("grant_type", "authorization_code");
        params.add("client_id", oAuthProvider.clientId());
        params.add("redirect_uri", oAuthProvider.redirectUri());
        params.add("code", code);

        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(params, httpHeaders);
        return restTemplate.postForObject(oAuthProvider.tokenUri(), requestEntity, String.class);
    }

    @Override
    public String getMemberInfoResponse(final String accessToken, final String userInfoUrl) {
        RestTemplate restTemplate = restTemplateConfig.restTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(accessToken);
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Void> requestEntity = new HttpEntity<>(headers);

        return restTemplate.postForObject(userInfoUrl, requestEntity, String.class);
    }
}
