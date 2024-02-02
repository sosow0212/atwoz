package com.atwoz.member.application.auth;

import com.atwoz.global.event.Events;
import com.atwoz.member.application.auth.dto.LoginRequest;
import com.atwoz.member.application.auth.dto.MemberInfo;
import com.atwoz.member.application.event.ValidatedLoginEvent;
import com.atwoz.member.domain.auth.OAuthRequester;
import com.atwoz.member.domain.auth.TokenProvider;
import com.atwoz.member.infrastructure.auth.dto.OAuthProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class AuthService {

    private final TokenProvider tokenProvider;
    private final OAuthRequester oAuthRequester;

    @Transactional
    public String login(final LoginRequest request, final OAuthProvider provider) {
        String accessToken = oAuthRequester.getAccessToken(request.code(), provider);
        MemberInfo memberInfo = oAuthRequester.getMemberInfo(accessToken, provider);
        Events.raise(new ValidatedLoginEvent(memberInfo.email(), memberInfo.name()));

        return tokenProvider.create(memberInfo.email());
    }
}
