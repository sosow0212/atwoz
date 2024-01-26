package com.atwoz.member.application.auth;

import com.atwoz.member.infrastructure.oauth.dto.MemberInfo;

public interface JsonMapper {

    String extractAccessTokenFrom(String accessTokenResponse);

    MemberInfo extractMemberInfoFrom(String memberInfoResponse);
}
