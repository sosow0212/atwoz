package com.atwoz.member.fixture.info;

import com.atwoz.member.domain.info.profile.Location;

@SuppressWarnings("NonAsciiCharacters")
public class LocationFixture {

    public static Location 일반_위치_생성() {
        String city = "서울시";
        String sector = "강남구";

        return new Location(city, sector);
    }

    public static Location 수정_위치_생성() {
        String city = "경기도";
        String sector = "성남시";

        return new Location(city, sector);
    }
}
