package com.atwoz.member.domain.info.option;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import com.atwoz.member.exception.exceptions.info.option.GraduateInvalidException;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@SuppressWarnings("NonAsciiCharacters")
public class GraduateTest {

    @Test
    void 올바른_선택_시_조회된다() {
        // given
        String graduateName = "로스쿨";

        // when & then
        assertDoesNotThrow(() -> Graduate.findByName(graduateName));
    }

    @Test
    void 올바른_최종_학력이_아니면_예외가_발생한다() {
        // given
        String graduateName = "hello";

        // when & then
        assertThatThrownBy(() -> Graduate.findByName(graduateName))
                .isInstanceOf(GraduateInvalidException.class);
    }
}
