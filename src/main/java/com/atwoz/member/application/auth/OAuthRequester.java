package com.atwoz.member.application.auth;

import com.atwoz.member.infrastructure.oauth.dto.MemberInfo;
import com.atwoz.member.ui.auth.support.oauth.OAuthProperties.OAuthProvider;

public interface OAuthRequester {

    String getAccessToken(String code, OAuthProvider provider);

    MemberInfo getMemberInfo(String accessToken, OAuthProvider oAuthProvider);
}



