package com.atwoz.member.application.member;

import com.atwoz.member.domain.member.Member;
import com.atwoz.member.domain.member.MemberRepository;
import com.atwoz.member.infrastructure.member.MemberFakeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator.ReplaceUnderscores;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayNameGeneration(ReplaceUnderscores.class)
@SuppressWarnings("NonAsciiCharacters")
@ExtendWith(MockitoExtension.class)
class MemberServiceTest {

    private MemberRepository memberRepository;
    private MemberService memberService;

    @BeforeEach
    void init() {
        memberRepository = new MemberFakeRepository();
        memberService = new MemberService(memberRepository);
    }

    @Test
    void 로그인을_한_이메일이_존재하지_않으면_회원_정보를_저장한다() {
        // given
        String email = "test@email.com";
        String nickname = "testNickname";

        // when
        memberService.create(email, nickname);

        // then
        Member member = memberRepository.findByEmail(email).get();
        assertThat(member.getNickname()).isEqualTo(nickname);
    }
}
