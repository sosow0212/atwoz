package com.atwoz.member.domain.info.profile.body;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import com.atwoz.member.domain.info.profile.body.Gender;
import com.atwoz.member.exception.exceptions.info.profile.body.InvalidGenderException;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@SuppressWarnings("NonAsciiCharacters")
class GenderTest {

    @Test
    void 올바른_선택_시_조회된다() {
        // given
        String genderName = "남성";

        // when & then
        assertDoesNotThrow(() -> Gender.findByName(genderName));
    }

    @Test
    void 올바른_성별이_아니면_예외가_발생한다() {
        // given
        String genderName = "hello";

        // when & then
        assertThatThrownBy(() -> Gender.findByName(genderName))
                .isInstanceOf(InvalidGenderException.class);
    }
}
