package com.atwoz.member.domain.auth;

import com.atwoz.member.application.auth.dto.MemberInfo;
import com.atwoz.member.infrastructure.auth.dto.OAuthProvider;

public interface OAuthRequester {

    String getAccessToken(String code, OAuthProvider provider);

    MemberInfo getMemberInfo(String accessToken, OAuthProvider oAuthProvider);
}



