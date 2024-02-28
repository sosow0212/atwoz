package com.atwoz.member.fixture.info.dto.response;

import com.atwoz.member.ui.info.dto.profile.LocationSearchResponse;

@SuppressWarnings("NonAsciiCharacters")
public class LocationSearchResponseFixture {

    public static LocationSearchResponse 위치_정보_조회_응답() {
        String city = "서울시";
        String sector = "강남구";

        return new LocationSearchResponse(city, sector);
    }
}
