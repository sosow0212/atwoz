package com.atwoz.member.application.member;

import com.atwoz.member.domain.info.hobby.Hobbies;
import com.atwoz.member.domain.info.style.Styles;
import com.atwoz.member.domain.member.Member;
import com.atwoz.member.domain.member.MemberRepository;
import com.atwoz.member.exception.exceptions.member.MemberNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class MemberService {

    private final MemberRepository memberRepository;

    @Transactional
    public void create(final String email, final String nickname) {
        if (!memberRepository.existsByEmail(email)) {
            memberRepository.save(Member.createWithOAuthLogin(email, nickname));
        }
    }

    @Transactional
    public void updateHobbies(final Long memberId, final Hobbies hobbies) {
        Member member = findById(memberId);
        member.updateHobbies(hobbies);
    }

    private Member findById(final Long memberId) {
        return memberRepository.findById(memberId)
                .orElseThrow(MemberNotFoundException::new);
    }

    @Transactional
    public void updateStyles(final Long memberId, final Styles styles) {
        Member member = findById(memberId);
        member.updateStyles(styles);
    }
}
