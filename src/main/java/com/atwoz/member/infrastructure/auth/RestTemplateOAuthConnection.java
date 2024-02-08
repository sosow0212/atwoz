package com.atwoz.member.infrastructure.auth;

import com.atwoz.member.config.RestTemplateConfig;
import com.atwoz.member.domain.auth.OAuthConnectionManager;
import com.atwoz.member.infrastructure.auth.dto.OAuthProviderRequest;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
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
    public String getAccessTokenResponse(final OAuthProviderRequest oAuthProviderRequest, final String code) {
        RestTemplate restTemplate = restTemplateConfig.restTemplate();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        String decode = URLDecoder.decode(code, StandardCharsets.UTF_8);

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("grant_type", "authorization_code");
        params.add("client_id", oAuthProviderRequest.clientId());
        params.add("client_secret", oAuthProviderRequest.clientSecret());
        params.add("redirect_uri", oAuthProviderRequest.redirectUri());
        params.add("code", decode);

        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(params, httpHeaders);
        return restTemplate.postForObject(oAuthProviderRequest.tokenUri(), requestEntity, String.class);
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
