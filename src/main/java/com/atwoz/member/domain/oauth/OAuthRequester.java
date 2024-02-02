package com.atwoz.member.domain.oauth;

import com.atwoz.member.infrastructure.oauth.dto.MemberInfo;
import com.atwoz.member.ui.auth.support.oauth.OAuthProvider;

public interface OAuthRequester {

    String getAccessToken(String code, OAuthProvider provider);

    MemberInfo getMemberInfo(String accessToken, OAuthProvider oAuthProvider);
}



