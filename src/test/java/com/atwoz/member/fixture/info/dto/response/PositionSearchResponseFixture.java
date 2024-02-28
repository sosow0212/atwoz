package com.atwoz.member.fixture.info.dto.response;

import com.atwoz.member.ui.info.dto.profile.PositionSearchResponse;
import java.math.BigDecimal;

@SuppressWarnings("NonAsciiCharacters")
public class PositionSearchResponseFixture {

    public static PositionSearchResponse 좌표_정보_조회_응답() {
        BigDecimal latitude = BigDecimal.valueOf(70.3);
        BigDecimal longitude = BigDecimal.valueOf(140.3);

        return new PositionSearchResponse(latitude, longitude);
    }
}
