package com.atwoz.member.fixture.info.dto.request;

import com.atwoz.member.application.info.dto.profile.position.PositionWriteRequest;
import java.math.BigDecimal;

@SuppressWarnings("NonAsciiCharacters")
public class PositionWriteRequestFixture {

    public static PositionWriteRequest 좌표_생성_요청() {
        BigDecimal latitude = BigDecimal.valueOf(70.39);
        BigDecimal longitude = BigDecimal.valueOf(140.38);

        return new PositionWriteRequest(latitude, longitude);
    }
}
