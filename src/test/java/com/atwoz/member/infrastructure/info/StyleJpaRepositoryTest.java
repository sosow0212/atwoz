package com.atwoz.member.infrastructure.info;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.SoftAssertions.assertSoftly;

import com.atwoz.member.domain.info.style.Style;
import com.atwoz.member.domain.info.style.StyleName;
import com.atwoz.member.domain.member.Member;
import com.atwoz.member.fixture.member.MemberFixture;
import com.atwoz.member.infrastructure.info.style.StyleJpaRepository;
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
class StyleJpaRepositoryTest {

    @Autowired
    private StyleJpaRepository styleJpaRepository;

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
        StyleName styleName = StyleName.POSITIVE;
        Long memberId = member.getId();
        Style memberStyle = new Style(memberId, styleName);

        // when
        Style saveStyle = styleJpaRepository.save(memberStyle);

        // then
        assertThat(saveStyle)
                .usingRecursiveComparison()
                .isEqualTo(memberStyle);
    }

    @Test
    void 회원_스타일을_조회한다() {
        // given
        StyleName styleName = StyleName.POSITIVE;
        Long memberId = member.getId();
        Style memberStyle = new Style(memberId, styleName);
        Style saveStyle = styleJpaRepository.save(memberStyle);

        // when
        List<Style> findMemberStyles = styleJpaRepository.findAllByMemberId(memberId);

        // then
        assertSoftly(softly -> {
            softly.assertThat(findMemberStyles.size()).isEqualTo(1);
            softly.assertThat(findMemberStyles).contains(saveStyle);
        });
    }

    @Test
    void 회원_스타일_목록을_저장_및_조회한다() {
        // given
        Long memberId = member.getId();
        List<Style> memberStyles = Stream.of(StyleName.POSITIVE, StyleName.GENTLE)
                .map(styleName -> new Style(memberId, styleName))
                .map(style -> styleJpaRepository.save(style))
                .toList();

        // when
        List<Style> findMemberStyles = styleJpaRepository.findAllByMemberId(memberId);

        // then
        assertSoftly(softly -> {
            softly.assertThat(findMemberStyles.size()).isEqualTo(2);
            softly.assertThat(findMemberStyles.containsAll(memberStyles)).isTrue();
        });
    }
}
