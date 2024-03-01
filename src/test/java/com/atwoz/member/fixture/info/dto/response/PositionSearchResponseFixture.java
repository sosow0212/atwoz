package com.atwoz.member.fixture.info.dto.response;

import com.atwoz.member.ui.info.dto.profile.PositionSearchResponse;
import java.math.BigDecimal;

@SuppressWarnings("NonAsciiCharacters")
public class PositionSearchResponseFixture {

    public static PositionSearchResponse 회원_좌표_조회_응답() {
        BigDecimal latitude = BigDecimal.valueOf(70.39);
        BigDecimal longitude = BigDecimal.valueOf(140.38);

        return new PositionSearchResponse(latitude, longitude);
    }
}
