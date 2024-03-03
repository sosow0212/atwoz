package com.atwoz.member.application.info.style;

import static com.atwoz.member.domain.info.style.Style.FASHION;
import static com.atwoz.member.domain.info.style.Style.FUNNY;
import static com.atwoz.member.domain.info.style.Style.GENTLE;
import static com.atwoz.member.domain.info.style.Style.POSITIVE;
import static com.atwoz.member.domain.info.style.Style.PURE;
import static com.atwoz.member.fixture.domain.info.style.MemberStylesFixture.회원_수정_스타일_생성;
import static com.atwoz.member.fixture.domain.info.style.MemberStylesFixture.회원_일반_스타일_생성;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.SoftAssertions.assertSoftly;

import com.atwoz.member.domain.info.style.MemberStyle;
import com.atwoz.member.domain.info.style.MemberStyleRepository;
import com.atwoz.member.exception.exceptions.info.style.InvalidStyleException;
import com.atwoz.member.exception.exceptions.info.style.StyleDuplicateException;
import com.atwoz.member.exception.exceptions.info.style.StyleSizeException;
import com.atwoz.member.infrastructure.info.style.MemberStyleFakeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import java.util.List;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@SuppressWarnings("NonAsciiCharacters")
class MemberStyleServiceTest {

    private MemberStyleService memberStyleService;
    private MemberStyleRepository memberStyleRepository;

    @BeforeEach
    void init() {
        memberStyleRepository = new MemberStyleFakeRepository();
        memberStyleService = new MemberStyleService(memberStyleRepository);
    }

    @Test
    void 회원의_모든_스타일을_저장한다() {
        // given
        Long memberId = 1L;
        List<String> styleCodes = List.of(POSITIVE.getCode(), GENTLE.getCode());
        List<MemberStyle> expectedMemberStyles = 회원_일반_스타일_생성();

        // when
        memberStyleService.saveMemberStyles(memberId, styleCodes);
        List<MemberStyle> saveMemberStyles = memberStyleRepository.findAllByMemberId(memberId);

        // then
        assertThat(saveMemberStyles).containsAll(expectedMemberStyles);
    }

    @Test
    void 회원의_스타일을_수정한다() {
        // given
        Long memberId = 1L;
        List<MemberStyle> originMemberStyles = 회원_일반_스타일_생성();
        List<MemberStyle> updateMemberStyles = 회원_수정_스타일_생성();
        List<String> updateStyleCodes = List.of(PURE.getCode(), FASHION.getCode());

        memberStyleRepository.saveAll(originMemberStyles);

        // when
        memberStyleService.updateMemberStyles(memberId, updateStyleCodes);
        List<MemberStyle> saveMemberStyles = memberStyleRepository.findAllByMemberId(memberId);

        // then
        assertSoftly(softly -> {
            softly.assertThat(saveMemberStyles).doesNotContainAnyElementsOf(originMemberStyles);
            softly.assertThat(saveMemberStyles).containsAll(updateMemberStyles);
        });
    }

    @Nested
    class 스타일_서비스_예외 {

        @Test
        void 제한을_벗어난_스타일을_저장하면_예외가_발생한다() {
            // given
            Long memberId = 1L;
            List<String> styleCodes = List.of(POSITIVE.getCode(), GENTLE.getCode(), FASHION.getCode(), FUNNY.getCode());

            // when & then
            assertThatThrownBy(() -> memberStyleService.saveMemberStyles(memberId, styleCodes))
                    .isInstanceOf(StyleSizeException.class);
        }

        @Test
        void 스타일을_아예_저장하지_않으면_예외가_발생한다() {
            // given
            Long memberId = 1L;
            List<String> styleCodes = List.of();

            // when & then
            assertThatThrownBy(() -> memberStyleService.saveMemberStyles(memberId, styleCodes))
                    .isInstanceOf(StyleSizeException.class);
        }

        @Test
        void 스타일을_중복_저장하면_예외가_발생한다() {
            // given
            Long memberId = 1L;
            List<String> styleCodes = List.of(POSITIVE.getCode(), POSITIVE.getCode());

            // when & then
            assertThatThrownBy(() -> memberStyleService.saveMemberStyles(memberId, styleCodes))
                    .isInstanceOf(StyleDuplicateException.class);
        }

        @Test
        void 없는_스타일을_저장하면_예외가_발생한다() {
            // given
            Long memberId = 1L;
            List<String> styleCodes = List.of("hello");

            // when & then
            assertThatThrownBy(() -> memberStyleService.saveMemberStyles(memberId, styleCodes))
                    .isInstanceOf(InvalidStyleException.class);
        }

        @Test
        void 제한을_벗어난_스타일을_수정하면_예외가_발생한다() {
            // given
            Long memberId = 1L;
            List<MemberStyle> originMemberStyles = 회원_일반_스타일_생성();
            List<String> updateStyleCodes = List.of(POSITIVE.getCode(), GENTLE.getCode(), FASHION.getCode(), FUNNY.getCode());

            memberStyleRepository.saveAll(originMemberStyles);

            // when & then
            assertThatThrownBy(() -> memberStyleService.updateMemberStyles(memberId, updateStyleCodes))
                    .isInstanceOf(StyleSizeException.class);
        }

        @Test
        void 수정할_스타일을_아예_선택하지_않으면_예외가_발생한다() {
            // given
            Long memberId = 1L;
            List<MemberStyle> originMemberStyles = 회원_일반_스타일_생성();
            List<String> updateStyleCodes = List.of();

            memberStyleRepository.saveAll(originMemberStyles);

            // when & then
            assertThatThrownBy(() -> memberStyleService.updateMemberStyles(memberId, updateStyleCodes))
                    .isInstanceOf(StyleSizeException.class);
        }

        @Test
        void 스타일을_중복_수정하면_예외가_발생한다() {
            // given
            Long memberId = 1L;
            List<MemberStyle> originMemberStyles = 회원_일반_스타일_생성();
            List<String> updateStyleCodes = List.of(POSITIVE.getCode(), POSITIVE.getCode());

            memberStyleRepository.saveAll(originMemberStyles);

            // when & then
            assertThatThrownBy(() -> memberStyleService.updateMemberStyles(memberId, updateStyleCodes))
                    .isInstanceOf(StyleDuplicateException.class);
        }

        @Test
        void 없는_스타일로_수정하면_예외가_발생한다() {
            // given
            Long memberId = 1L;
            List<MemberStyle> originMemberStyles = 회원_일반_스타일_생성();
            List<String> updateStyleCodes = List.of("hello");

            memberStyleRepository.saveAll(originMemberStyles);

            // when & then
            assertThatThrownBy(() -> memberStyleService.updateMemberStyles(memberId, updateStyleCodes))
                    .isInstanceOf(InvalidStyleException.class);
        }
    }
}
