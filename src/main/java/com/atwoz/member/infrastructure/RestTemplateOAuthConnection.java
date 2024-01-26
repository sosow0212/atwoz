package com.atwoz.member.infrastructure;

import com.atwoz.member.application.auth.OAuthConnectionManager;
import com.atwoz.member.config.oauth.OAuthProvider;
import java.net.URI;
import java.net.URISyntaxException;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Component
public class RestTemplateOAuthConnection implements OAuthConnectionManager {

    @Override
    public String getAccessTokenResponse(final OAuthProvider oAuthProvider, final String code) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("grant_type", "authorization_code");
        params.add("client_id", oAuthProvider.getClientId());
        params.add("redirect_uri", oAuthProvider.getRedirectUrl());
        params.add("code", code);

        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(params, httpHeaders);
        return restTemplate.postForObject(oAuthProvider.getTokenUrl(), requestEntity, String.class);
    }

    @Override
    public String extractRealInfo(final String accessToken, final String userInfoUrl) {
        try {
            RestTemplate restTemplate = new RestTemplate();
            HttpHeaders headers = new HttpHeaders();
            headers.setBearerAuth(accessToken);
            headers.setContentType(MediaType.APPLICATION_JSON);
            RequestEntity<Void> requestEntity = new RequestEntity<>(headers, HttpMethod.GET, new URI(userInfoUrl));
            ResponseEntity<String> responseEntity = restTemplate.exchange(requestEntity, String.class);

            return responseEntity.getBody();
        } catch (URISyntaxException exception) {
            throw new RuntimeException();
        }
    }
}
