package com.atwoz.member.domain.auth;

import com.atwoz.member.application.auth.dto.MemberInfo;

public interface JsonMapper {

    String extractAccessTokenFrom(String accessTokenResponse);

    MemberInfo extractMemberInfoFrom(String memberInfoResponse);

    String extractValueForKey(String json, String key);
}
