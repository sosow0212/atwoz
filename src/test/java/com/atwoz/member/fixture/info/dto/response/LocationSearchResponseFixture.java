package com.atwoz.member.fixture.info.dto.response;

import com.atwoz.member.infrastructure.info.profile.location.dto.LocationSearchResponse;

@SuppressWarnings("NonAsciiCharacters")
public class LocationSearchResponseFixture {

    public static LocationSearchResponse 회원_위치_조회_응답() {
        String city = "서울시";
        String sector = "강남구";

        return new LocationSearchResponse(city, sector);
    }
}
