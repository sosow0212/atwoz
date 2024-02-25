package com.atwoz.member.domain.info.style;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.SoftAssertions.assertSoftly;

import com.atwoz.member.exception.exceptions.info.style.StyleDuplicateException;
import com.atwoz.member.exception.exceptions.info.style.StyleNotFoundException;
import com.atwoz.member.exception.exceptions.info.style.StyleSizeException;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import java.util.List;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@SuppressWarnings("NonAsciiCharacters")
class StyleNameTest {

    @Test
    void 스타일이_존재하면_정상적으로_가져온다() {
        // given
        List<String> styleCodes = List.of("C001", "C002", "C003");
        List<StyleName> expectedStyles = List.of(StyleName.FASHION, StyleName.FRIENDLY, StyleName.FUNNY);

        // when
        List<StyleName> findStyles = StyleName.findAllByCodes(styleCodes);

        // then
        assertSoftly(softly -> {
            softly.assertThat(findStyles.size()).isEqualTo(styleCodes.size());
            softly.assertThat(findStyles).containsAll(expectedStyles);
        });
    }

    @Test
    void 스타일_갯수가_초과되면_예외가_발생한다() {
        // given
        List<String> styleCodes = List.of("C001", "C002", "C003", "C004");

        // when & then
        assertThatThrownBy(() -> StyleName.findAllByCodes(styleCodes))
                .isInstanceOf(StyleSizeException.class);
    }

    @Test
    void 아예_선택하지_않으면_예외가_발생한다() {
        // given
        List<String> styleCodes = List.of();

        // when & then
        assertThatThrownBy(() -> StyleName.findAllByCodes(styleCodes))
                .isInstanceOf(StyleSizeException.class);
    }

    @Test
    void 없는_스타일을_선택하면_예외가_발생한다() {
        // given
        List<String> styleCodes = List.of("ABCD");

        // when & then
        assertThatThrownBy(() -> StyleName.findAllByCodes(styleCodes))
                .isInstanceOf(StyleNotFoundException.class);
    }

    @Test
    void 중복_선택하면_예외가_발생한다() {
        // given
        List<String> styleCodes = List.of("C001", "C001");

        // when & then
        assertThatThrownBy(() -> StyleName.findAllByCodes(styleCodes))
                .isInstanceOf(StyleDuplicateException.class);
    }
}
