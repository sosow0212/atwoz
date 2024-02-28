package com.atwoz.member.domain.info.option;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import com.atwoz.member.exception.exceptions.info.option.SmokeInvalidException;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@SuppressWarnings("NonAsciiCharacters")
public class SmokeTest {

    @Test
    void 올바른_선택_시_조회된다() {
        // given
        String smokeName = "비흡연";

        // when & then
        assertDoesNotThrow(() -> Smoke.findByName(smokeName));
    }

    @Test
    void 올바른_흡연_단계가_아니면_예외가_발생한다() {
        // given
        String smokeName = "hello";

        // when & then
        assertThatThrownBy(() -> Smoke.findByName(smokeName))
                .isInstanceOf(SmokeInvalidException.class);
    }
}
