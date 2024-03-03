package com.atwoz.member.domain.member;

import com.atwoz.member.infrastructure.member.NicknameFakeGenerator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import static com.atwoz.member.fixture.domain.member.MemberFixture.어드민_유저_생성;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.SoftAssertions.assertSoftly;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@SuppressWarnings("NonAsciiCharacters")
class MemberTest {

    private NicknameGenerator nicknameGenerator;

    @BeforeEach
    void setup() {
        nicknameGenerator = new NicknameFakeGenerator();
    }

    @Test
    void 어드민인_경우에_true를_반환한다() {
        // given
        Member admin = 어드민_유저_생성();

        // when
        boolean result = admin.isAdmin();

        // then
        assertThat(result).isTrue();
    }

    @Test
    void 회원가입시_기본적으로_MEMBER_ROLE과_랜덤한_닉네임으로_생성된다() {
        // when
        Member member = Member.createDefaultRole("email@email.com", nicknameGenerator);

        // then
        assertSoftly(softly -> {
            softly.assertThat(member.getMemberRole()).isEqualTo(MemberRole.MEMBER);
            softly.assertThat(member.getNickname()).isEqualTo("nickname");
        });
    }
}
