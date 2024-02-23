package com.atwoz.member.domain.info.profile;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import com.atwoz.member.exception.exceptions.info.profile.ProfileRangeException;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.stream.Stream;

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

    @ParameterizedTest(name = "출생년도 {0}, 키 {1}인 경우")
    @MethodSource("birthYearAndHeight")
    void 키_또는_출생년도_제한을_벗어나면_예외가_발생한다(final int birthYear, final int height) {
        // given
        int currentYear = 2024;
        Gender gender = Gender.MALE;

        // when & then
        assertThatThrownBy(() -> new Body(currentYear, birthYear, height, gender))
                .isInstanceOf(ProfileRangeException.class);
    }

    static Stream<Arguments> birthYearAndHeight() {
        return Stream.of(
                Arguments.arguments(2010, 171),
                Arguments.arguments(1978, 171),
                Arguments.arguments(2000, 130),
                Arguments.arguments(2000, 200)
        );
    }
}
