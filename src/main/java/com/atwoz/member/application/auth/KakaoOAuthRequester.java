package com.atwoz.member.application.auth;

import com.atwoz.member.application.auth.dto.MemberInfo;
import com.atwoz.member.domain.auth.JsonMapper;
import com.atwoz.member.domain.auth.OAuthConnectionManager;
import com.atwoz.member.infrastructure.auth.dto.OAuthProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class KakaoOAuthRequester implements OAuthRequester {

    private final OAuthConnectionManager oAuthConnectionManager;
    private final JsonMapper jsonMapper;

    @Override
    public String getAccessToken(final String code, final OAuthProvider provider) {
        String accessTokenResponse = oAuthConnectionManager.getAccessTokenResponse(provider, code);
        return jsonMapper.extractAccessTokenFrom(accessTokenResponse);
    }

    @Override
    public MemberInfo getMemberInfo(final String accessToken, final OAuthProvider oAuthProvider) {
        String memberInfoResponse = oAuthConnectionManager.getMemberInfoResponse(accessToken,
                oAuthProvider.userInfoUri());

        return jsonMapper.extractMemberInfoFrom(memberInfoResponse);
    }
}
