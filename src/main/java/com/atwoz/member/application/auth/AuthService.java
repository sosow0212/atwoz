package com.atwoz.member.application.auth;

import com.atwoz.global.event.Events;
import com.atwoz.member.application.auth.dto.LoginRequest;
import com.atwoz.member.application.event.ValidatedLoginEvent;
import com.atwoz.member.domain.auth.TokenProvider;
import com.atwoz.member.infrastructure.auth.dto.MemberInfoResponse;
import com.atwoz.member.infrastructure.auth.dto.OAuthProviderRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class AuthService {

    private final TokenProvider tokenProvider;
    private final OAuthRequester oAuthRequester;

    @Transactional
    public String login(final LoginRequest request, final OAuthProviderRequest provider) {
        String accessToken = oAuthRequester.getAccessToken(request.code(), provider);
        MemberInfoResponse memberInfoResponse = oAuthRequester.getMemberInfo(accessToken, provider);
        Events.raise(new ValidatedLoginEvent(memberInfoResponse.email(), memberInfoResponse.name()));

        return tokenProvider.createTokenWith(memberInfoResponse.email());
    }
}
