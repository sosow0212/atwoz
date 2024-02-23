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
class PositionTest {

    @Test
    void 입력값이_올바른_경우_생성된다() {
        // given
        double latitude = 40;
        double longitude = 130;

        // when & then
        assertDoesNotThrow(() -> new Position(latitude, longitude));
    }

    @ParameterizedTest(name = "위도 {0}, 경도 {1}인 경우")
    @MethodSource("latitudeAndLongitude")
    void 위도_또는_경도_제한을_벗어나면_예외가_발생한다(final double latitude, final double longitude) {
        // when & then
        assertThatThrownBy(() -> new Position(latitude, longitude))
                .isInstanceOf(ProfileRangeException.class);
    }

    static Stream<Arguments> latitudeAndLongitude() {
        return Stream.of(
                Arguments.of(-100, 130),
                Arguments.of(100, 130),
                Arguments.of(40, -200),
                Arguments.of(40, 200)
        );
    }
}
