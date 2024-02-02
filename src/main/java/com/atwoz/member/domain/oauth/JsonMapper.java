package com.atwoz.member.domain.oauth;

import com.atwoz.member.infrastructure.oauth.dto.MemberInfo;

public interface JsonMapper {

    String extractAccessTokenFrom(String accessTokenResponse);

    MemberInfo extractMemberInfoFrom(String memberInfoResponse);

    String extractValueForKey(String json, String key);
}
