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
public class StyleNameTest {

    @Test
    void 스타일이_존재하면_정상적으로_가져온다() {
        // given
        List<String> styleNames = List.of("패션 센스", "다정다감", "유머 감각");
        List<StyleName> expectedStyleNames = List.of(StyleName.FASHION, StyleName.FRIENDLY, StyleName.FUNNY);

        // when
        List<StyleName> findStyleNames = StyleName.findAllByNames(styleNames);

        // then
        assertSoftly(softly -> {
            softly.assertThat(findStyleNames.size()).isEqualTo(styleNames.size());
            softly.assertThat(findStyleNames).containsAll(expectedStyleNames);
        });
    }

    @Test
    void 스타일_갯수가_초과되면_예외가_발생한다() {
        // given
        List<String> styleNames = List.of("패션 센스", "다정다감", "유머 감각", "좋은 비율");

        // when & then
        assertThatThrownBy(() -> StyleName.findAllByNames(styleNames))
                .isInstanceOf(StyleSizeException.class);
    }

    @Test
    void 아예_선택하지_않으면_예외가_발생한다() {
        // given
        List<String> styleNames = List.of();

        // when & then
        assertThatThrownBy(() -> StyleName.findAllByNames(styleNames))
                .isInstanceOf(StyleSizeException.class);
    }

    @Test
    void 없는_스타일을_선택하면_예외가_발생한다() {
        // given
        List<String> styleNames = List.of("hello");

        // when & then
        assertThatThrownBy(() -> StyleName.findAllByNames(styleNames))
                .isInstanceOf(StyleNotFoundException.class);
    }

    @Test
    void 중복_선택하면_예외가_발생한다() {
        // given
        List<String> styleNames = List.of("패션 센스", "패션 센스");

        // when & then
        assertThatThrownBy(() -> StyleName.findAllByNames(styleNames))
                .isInstanceOf(StyleDuplicateException.class);
    }
}
