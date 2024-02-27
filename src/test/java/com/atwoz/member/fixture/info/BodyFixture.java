package com.atwoz.member.fixture.info;

import com.atwoz.member.domain.info.profile.Body;
import com.atwoz.member.domain.info.profile.Gender;
import com.atwoz.member.domain.info.profile.YearManager;
import com.atwoz.member.infrastructure.info.FakeYearManager;

public class BodyFixture {

    private static final int BIRTH_YEAR = 2000;
    private static final int HEIGHT = 171;

    public static Body 일반_body_생성() {
        YearManager yearManager = new FakeYearManager();
        return new Body(yearManager, BIRTH_YEAR, HEIGHT, Gender.MALE);
    }
}
