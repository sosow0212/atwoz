package com.atwoz.member.infrastructure.auth;

import com.atwoz.member.application.auth.dto.MemberInfo;
import com.atwoz.member.domain.auth.JsonMapper;
import com.atwoz.member.exception.exceptions.auth.JsonDataInvalidException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

@Component
public class JackonJsonMapper implements JsonMapper {

    @Override
    public String extractAccessTokenFrom(final String accessTokenResponse) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            JsonNode jsonNode = objectMapper.readTree(accessTokenResponse);
            return jsonNode.get("access_token").asText();

        } catch (JsonProcessingException exception) {
            throw new JsonDataInvalidException();
        }
    }

    @Override
    public MemberInfo extractMemberInfoFrom(final String memberInfoResponse) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            JsonNode jsonNode = objectMapper.readTree(memberInfoResponse);
            String email = jsonNode.get("kakao_account").get("email").asText();
            String nickname = jsonNode.get("kakao_account").get("profile").get("nickname").asText();
            return new MemberInfo(email, nickname);

        } catch (JsonProcessingException exception) {
            throw new JsonDataInvalidException();
        }
    }

    @Override
    public String extractValueForKey(final String json, final String key) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            JsonNode jsonNode = objectMapper.readTree(json);
            return jsonNode.get(key).asText();

        } catch (JsonProcessingException exception) {
            throw new JsonDataInvalidException();
        }
    }
}