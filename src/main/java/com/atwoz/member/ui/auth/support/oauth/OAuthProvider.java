package com.atwoz.member.ui.auth.support.oauth;

import com.atwoz.member.ui.auth.support.oauth.OAuthProperties.Provider;
import com.atwoz.member.ui.auth.support.oauth.OAuthProperties.User;
import lombok.Builder;
import lombok.Getter;

@Getter
public class OAuthProvider {

    private final String clientId;
    private final String redirectUrl;
    private final String tokenUrl;
    private final String userInfoUrl;

    public OAuthProvider(final User user, final Provider provider) {
        this(user.getClientId(), user.getRedirectUri(), provider.getTokenUri(), provider.getUserInfoUri());
    }

    @Builder
    public OAuthProvider(final String clientId, final String redirectUrl, final String tokenUrl,
                         final String userInfoUrl) {
        this.clientId = clientId;
        this.redirectUrl = redirectUrl;
        this.tokenUrl = tokenUrl;
        this.userInfoUrl = userInfoUrl;
    }
}
