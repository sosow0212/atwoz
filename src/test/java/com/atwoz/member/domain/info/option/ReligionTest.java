package com.atwoz.member.domain.info.option;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import com.atwoz.member.exception.exceptions.info.option.ReligionInvalidException;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@SuppressWarnings("NonAsciiCharacters")
public class ReligionTest {

    @Test
    void 올바른_선택_시_조회된다() {
        // given
        String religionName = "기독교";

        // when & then
        assertDoesNotThrow(() -> Religion.findByName(religionName));
    }

    @Test
    void 올바른_종교가_아니면_예외가_발생한다() {
        // given
        String religionName = "hello";

        // when & then
        assertThatThrownBy(() -> Religion.findByName(religionName))
                .isInstanceOf(ReligionInvalidException.class);
    }
}
