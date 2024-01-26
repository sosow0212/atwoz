package com.atwoz.member.infrastructure.oauth;

import com.atwoz.member.application.auth.JsonMapper;
import com.atwoz.member.application.auth.OAuthConnectionManager;
import com.atwoz.member.application.auth.OAuthRequester;
import com.atwoz.member.infrastructure.oauth.dto.MemberInfo;
import com.atwoz.member.ui.auth.support.oauth.OAuthProperties.OAuthProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
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
                oAuthProvider.getUserInfoUri());

        return jsonMapper.extractMemberInfoFrom(memberInfoResponse);
    }
}
