package com.atwoz.member.fixture.info;

import com.atwoz.member.domain.info.profile.Position;
import java.math.BigDecimal;

@SuppressWarnings("NonAsciiCharacters")
public class PositionFixture {

    public static Position 회원_일반_좌표_생성() {
        BigDecimal latitude = BigDecimal.valueOf(70.39);
        BigDecimal longitude = BigDecimal.valueOf(140.38);

        return new Position(latitude, longitude);
    }

    public static Position 회원_수정_좌표_생성() {
        BigDecimal latitude = BigDecimal.valueOf(60.2);
        BigDecimal longitude = BigDecimal.valueOf(130.3);

        return new Position(latitude, longitude);
    }
}
