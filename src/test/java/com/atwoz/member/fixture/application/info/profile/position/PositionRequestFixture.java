package com.atwoz.member.fixture.application.info.profile.position;

import com.atwoz.member.application.info.profile.position.dto.PositionUpdateRequest;
import com.atwoz.member.application.info.profile.position.dto.PositionWriteRequest;
import java.math.BigDecimal;

@SuppressWarnings("NonAsciiCharacters")
public class PositionRequestFixture {

    public static PositionWriteRequest 회원_좌표_생성_요청() {
        BigDecimal latitude = BigDecimal.valueOf(70.39);
        BigDecimal longitude = BigDecimal.valueOf(140.38);

        return new PositionWriteRequest(latitude, longitude);
    }

    public static PositionUpdateRequest 회원_좌표_수정_요청() {
        BigDecimal latitude = BigDecimal.valueOf(60.2);
        BigDecimal longitude = BigDecimal.valueOf(130.3);

        return new PositionUpdateRequest(latitude, longitude);
    }
}
