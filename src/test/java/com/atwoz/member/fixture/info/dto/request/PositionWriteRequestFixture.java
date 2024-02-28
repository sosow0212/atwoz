package com.atwoz.member.fixture.info.dto.request;

import com.atwoz.member.application.info.dto.profile.PositionWriteRequest;
import java.math.BigDecimal;

@SuppressWarnings("NonAsciiCharacters")
public class PositionWriteRequestFixture {

    public static PositionWriteRequest 좌표_생성_요청() {
        BigDecimal latitude = BigDecimal.valueOf(70.3);
        BigDecimal longitude = BigDecimal.valueOf(140.3);

        return new PositionWriteRequest(latitude, longitude);
    }
}
