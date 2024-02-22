package com.atwoz.member.fixture.info;

import com.atwoz.member.domain.info.profile.Body;
import com.atwoz.member.domain.info.profile.Gender;

public class BodyFixture {

    private static final int CURRENT_YEAR = 2024;
    private static final int BIRTH_YEAR = 2000;
    private static final int HEIGHT = 171;

    public static Body 일반_body_생성() {
        return new Body(CURRENT_YEAR, BIRTH_YEAR, HEIGHT, Gender.MALE);
    }
}
