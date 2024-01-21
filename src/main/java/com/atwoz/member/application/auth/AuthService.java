package com.atwoz.member.application.auth;

import com.atwoz.global.event.Events;
import com.atwoz.member.application.auth.dto.LoginRequest;
import com.atwoz.member.application.auth.dto.SignupRequest;
import com.atwoz.member.domain.auth.RegisteredEvent;
import com.atwoz.member.domain.auth.TokenProvider;
import com.atwoz.member.domain.member.Member;
import com.atwoz.member.domain.member.MemberRepository;
import com.atwoz.member.domain.member.NicknameGenerator;
import com.atwoz.member.exception.exceptions.member.MemberAlreadyExistedException;
import com.atwoz.member.exception.exceptions.member.MemberNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class AuthService {

    private final MemberRepository memberRepository;
    private final TokenProvider tokenProvider;
    private final NicknameGenerator nicknameGenerator;

    @Transactional
    public String signup(final SignupRequest request) {
        validateExistedMember(request.email());

        Member member = Member.createDefaultRole(request.email(), request.password(), nicknameGenerator);
        Member signupMember = memberRepository.save(member);
        Events.raise(new RegisteredEvent(member.getId(), member.getEmail(), member.getNickname()));

        return tokenProvider.create(signupMember.getId());
    }

    private void validateExistedMember(final String email) {
        if (memberRepository.existsByEmail(email)) {
            throw new MemberAlreadyExistedException();
        }
    }

    @Transactional(readOnly = true)
    public String login(final LoginRequest request) {
        Member member = findMemberByEmail(request.email());
        member.validatePassword(request.password());

        return tokenProvider.create(member.getId());
    }

    private Member findMemberByEmail(final String email) {
        return memberRepository.findByEmail(email)
                .orElseThrow(MemberNotFoundException::new);
    }
}
