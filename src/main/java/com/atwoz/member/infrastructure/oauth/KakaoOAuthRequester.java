package com.atwoz.member.infrastructure.oauth;

import com.atwoz.member.domain.oauth.JsonMapper;
import com.atwoz.member.domain.oauth.OAuthConnectionManager;
import com.atwoz.member.domain.oauth.OAuthRequester;
import com.atwoz.member.infrastructure.oauth.dto.MemberInfo;
import com.atwoz.member.ui.auth.support.oauth.OAuthProvider;
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
                oAuthProvider.userInfoUri());

        return jsonMapper.extractMemberInfoFrom(memberInfoResponse);
    }
}
