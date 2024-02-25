package com.atwoz.member.domain.info.profile;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import com.atwoz.member.exception.exceptions.info.profile.position.LatitudeRangeException;
import com.atwoz.member.exception.exceptions.info.profile.position.LongitudeRangeException;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import java.math.BigDecimal;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@SuppressWarnings("NonAsciiCharacters")
class PositionTest {

    @Test
    void 입력값이_올바른_경우_생성된다() {
        // given
        BigDecimal latitude = BigDecimal.valueOf(40);
        BigDecimal longitude = BigDecimal.valueOf(130);

        // when & then
        assertDoesNotThrow(() -> new Position(latitude, longitude));
    }

    @ParameterizedTest(name = "위도가 {0}인 경우")
    @ValueSource(ints = {-100, 100})
    void 위도_제한을_벗어나면_예외가_발생한다(final int lat) {
        // given
        BigDecimal latitude = BigDecimal.valueOf(lat);
        BigDecimal longitude = BigDecimal.valueOf(130);

        // when & then
        assertThatThrownBy(() -> new Position(latitude, longitude))
                .isInstanceOf(LatitudeRangeException.class);
    }

    @ParameterizedTest(name = "경도가 {0}인 경우")
    @ValueSource(ints = {-200, 200})
    void 경도_제한을_벗어나면_예외가_발생한다(final int lon) {
        // given
        BigDecimal latitude = BigDecimal.valueOf(40);
        BigDecimal longitude = BigDecimal.valueOf(lon);

        // when & then
        assertThatThrownBy(() -> new Position(latitude, longitude))
                .isInstanceOf(LongitudeRangeException.class);
    }
}
