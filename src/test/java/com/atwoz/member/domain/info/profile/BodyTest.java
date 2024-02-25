package com.atwoz.member.domain.info.profile;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import com.atwoz.member.exception.exceptions.info.profile.body.AgeRangeException;
import com.atwoz.member.exception.exceptions.info.profile.body.HeightRangeException;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@SuppressWarnings("NonAsciiCharacters")
class BodyTest {

    @Test
    void 입력값이_올바른_경우_생성된다() {
        // given
        int currentYear = 2024;
        int birthYear = 2000;
        int height = 171;
        Gender gender = Gender.MALE;

        // when & then
        assertDoesNotThrow(() -> new Body(currentYear, birthYear, height, gender));
    }

    @ParameterizedTest(name = "출생년도가 {0}인 경우")
    @ValueSource(ints = {2010, 1978})
    void 연도_제한을_벗어나면_예외가_발생한다(final int birthYear) {
        // given
        int currentYear = 2024;
        int height = 171;
        Gender gender = Gender.MALE;

        // when & then
        assertThatThrownBy(() -> new Body(currentYear, birthYear, height, gender))
                .isInstanceOf(AgeRangeException.class);
    }

    @ParameterizedTest(name = "키가 {0}인 경우")
    @ValueSource(ints = {130, 500})
    void 키_제한을_벗어나면_예외가_발생한다(final int height) {
        // given
        int currentYear = 2024;
        int birthYear = 2000;
        Gender gender = Gender.MALE;

        // when & then
        assertThatThrownBy(() -> new Body(currentYear, birthYear, height, gender))
                .isInstanceOf(HeightRangeException.class);
    }
}
