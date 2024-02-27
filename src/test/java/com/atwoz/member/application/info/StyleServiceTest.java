package com.atwoz.member.application.info;

import static com.atwoz.member.domain.info.style.StyleName.GENTLE;
import static com.atwoz.member.domain.info.style.StyleName.POSITIVE;
import static org.assertj.core.api.Assertions.assertThat;

import com.atwoz.member.application.info.style.StyleService;
import com.atwoz.member.domain.info.style.Style;
import com.atwoz.member.domain.info.style.StyleRepository;
import com.atwoz.member.infrastructure.info.StyleFakeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import java.util.List;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@SuppressWarnings("NonAsciiCharacters")
class StyleServiceTest {

    private StyleService styleService;
    private StyleRepository styleRepository;

    @BeforeEach
    void init() {
        styleRepository = new StyleFakeRepository();
        styleService = new StyleService(styleRepository);
    }

    @Test
    void 회원의_모든_스타일을_저장한다() {
        // given
        Long memberId = 1L;
        List<String> styleCodes = List.of(POSITIVE.getCode(), GENTLE.getCode());

        // when
        styleService.saveMemberStyles(memberId, styleCodes);

        // then
        List<Style> saveStyles = styleRepository.findAllByMemberId(memberId);
        assertThat(saveStyles.size()).isEqualTo(2);
    }

    @Test
    void 회원의_모든_스타일을_삭제한다() {
        // given
        Long memberId = 1L;
        List<String> styleCodes = List.of(POSITIVE.getCode(), GENTLE.getCode());
        styleService.saveMemberStyles(memberId, styleCodes);

        // when
        styleService.deleteMemberStyles(memberId);

        // then
        assertThat(styleRepository.findAllByMemberId(memberId)).isEmpty();
    }

    @Test
    void 회원의_모든_스타일을_조회한다() {
        // given
        Long memberId = 1L;
        List<String> styleCodes = List.of(POSITIVE.getCode(), GENTLE.getCode());
        styleService.saveMemberStyles(memberId, styleCodes);

        // when
        List<Style> memberStyles = styleService.findMemberStyles(memberId);

        // then
        assertThat(memberStyles.size()).isEqualTo(2);
    }
}
