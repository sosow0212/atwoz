package com.atwoz.member.fixture.info;

import com.atwoz.member.domain.info.profile.Position;

public class PositionFixture {

    public static Position 일반_좌표_생성() {
        double latitude = 40;
        double longitude = 170;

        return new Position(latitude, longitude);
    }
}
