package com.atwoz.member.fixture.info.dto.request;

import com.atwoz.member.application.info.dto.profile.location.LocationWriteRequest;

@SuppressWarnings("NonAsciiCharacters")
public class LocationWriteRequestFixture {

    public static LocationWriteRequest 회원_위치_생성_요청() {
        String city = "서울시";
        String sector = "강남구";
        
        return new LocationWriteRequest(city, sector);
    }
}
