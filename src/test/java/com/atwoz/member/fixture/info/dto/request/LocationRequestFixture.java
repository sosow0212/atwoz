package com.atwoz.member.fixture.info.dto.request;

import com.atwoz.member.application.info.dto.profile.location.LocationUpdateRequest;
import com.atwoz.member.application.info.dto.profile.location.LocationWriteRequest;

@SuppressWarnings("NonAsciiCharacters")
public class LocationRequestFixture {

    public static LocationWriteRequest 회원_위치_생성_요청() {
        String city = "서울시";
        String sector = "강남구";

        return new LocationWriteRequest(city, sector);
    }

    public static LocationUpdateRequest 회원_위치_수정_요청() {
        String city = "경기도";
        String sector = "성남시";

        return new LocationUpdateRequest(city, sector);
    }
}
