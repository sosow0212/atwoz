package com.atwoz.member.fixture.info;

import com.atwoz.member.domain.info.profile.Position;
import java.math.BigDecimal;

@SuppressWarnings("NonAsciiCharacters")
public class PositionFixture {

    public static Position 일반_좌표_생성() {
        BigDecimal latitude = BigDecimal.valueOf(40);
        BigDecimal longitude = BigDecimal.valueOf(170);

        return new Position(latitude, longitude);
    }

    public static Position 수정_좌표_생성() {
        BigDecimal latitude = BigDecimal.valueOf(50);
        BigDecimal longitude = BigDecimal.valueOf(160);

        return new Position(latitude, longitude);
    }
}
