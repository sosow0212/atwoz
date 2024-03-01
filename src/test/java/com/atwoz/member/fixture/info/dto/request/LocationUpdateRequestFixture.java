package com.atwoz.member.fixture.info.dto.request;

import com.atwoz.member.application.info.dto.profile.location.LocationUpdateRequest;

@SuppressWarnings("NonAsciiCharacters")
public class LocationUpdateRequestFixture {

    public static LocationUpdateRequest 회원_위치_수정_요청() {
        String city = "경기도";
        String sector = "성남시";

        return new LocationUpdateRequest(city, sector);
    }
}
