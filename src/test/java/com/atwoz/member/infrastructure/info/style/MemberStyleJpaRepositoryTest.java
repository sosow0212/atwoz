package com.atwoz.member.infrastructure.info.style;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.SoftAssertions.assertSoftly;

import com.atwoz.member.domain.info.style.MemberStyle;
import com.atwoz.member.domain.info.style.Style;
import com.atwoz.member.domain.member.Member;
import com.atwoz.member.fixture.domain.member.MemberFixture;
import com.atwoz.member.infrastructure.info.style.MemberStyleJpaRepository;
import com.atwoz.member.infrastructure.member.MemberJpaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import java.util.List;
import java.util.stream.Stream;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@SuppressWarnings("NonAsciiCharacters")
@DataJpaTest
class MemberStyleJpaRepositoryTest {

    @Autowired
    private MemberStyleJpaRepository memberStyleJpaRepository;

    @Autowired
    private MemberJpaRepository memberJpaRepository;

    private Member member;

    @BeforeEach
    void init() {
        member = memberJpaRepository.save(MemberFixture.일반_유저_생성());
    }

    @Test
    void 회원_스타일을_저장한다() {
        // given
        Style style = Style.POSITIVE;
        Long memberId = member.getId();
        MemberStyle memberStyle = new MemberStyle(memberId, style);

        // when
        MemberStyle saveMemberStyle = memberStyleJpaRepository.save(memberStyle);

        // then
        assertThat(saveMemberStyle)
                .usingRecursiveComparison()
                .ignoringFields("id")
                .isEqualTo(memberStyle);
    }

    @Test
    void 회원_스타일을_조회한다() {
        // given
        Style style = Style.POSITIVE;
        Long memberId = member.getId();
        MemberStyle memberStyle = new MemberStyle(memberId, style);
        MemberStyle saveMemberStyle = memberStyleJpaRepository.save(memberStyle);

        // when
        List<MemberStyle> findMemberStyles = memberStyleJpaRepository.findAllByMemberId(memberId);

        // then
        assertSoftly(softly -> {
            softly.assertThat(findMemberStyles.size()).isEqualTo(1);
            softly.assertThat(findMemberStyles).contains(saveMemberStyle);
        });
    }

    @Test
    void 회원_스타일_목록을_저장_및_조회한다() {
        // given
        Long memberId = member.getId();
        List<MemberStyle> memberStyles = Stream.of(Style.POSITIVE, Style.GENTLE)
                .map(style -> new MemberStyle(memberId, style))
                .map(memberStyle -> memberStyleJpaRepository.save(memberStyle))
                .toList();

        // when
        List<MemberStyle> findMemberStyles = memberStyleJpaRepository.findAllByMemberId(memberId);

        // then
        assertSoftly(softly -> {
            softly.assertThat(findMemberStyles.size()).isEqualTo(2);
            softly.assertThat(findMemberStyles.containsAll(memberStyles)).isTrue();
        });
    }
}
