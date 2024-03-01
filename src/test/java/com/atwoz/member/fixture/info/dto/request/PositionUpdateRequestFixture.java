package com.atwoz.member.fixture.info.dto.request;

import com.atwoz.member.application.info.dto.profile.position.PositionUpdateRequest;
import java.math.BigDecimal;

@SuppressWarnings("NonAsciiCharacters")
public class PositionUpdateRequestFixture {

    public static PositionUpdateRequest 회원_좌표_수정_요청() {
        BigDecimal latitude = BigDecimal.valueOf(60.2);
        BigDecimal longitude = BigDecimal.valueOf(130.3);

        return new PositionUpdateRequest(latitude, longitude);
    }
}
