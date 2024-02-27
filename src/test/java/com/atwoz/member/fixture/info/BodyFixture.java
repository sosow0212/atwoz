package com.atwoz.member.fixture.info;

import com.atwoz.member.domain.info.profile.Body;
import com.atwoz.member.domain.info.profile.Gender;
import com.atwoz.member.domain.info.profile.YearManager;
import com.atwoz.member.infrastructure.info.FakeYearManager;

public class BodyFixture {

    public static Body 일반_body_생성() {
        YearManager yearManager = new FakeYearManager();
        int birthYear = 2000;
        int height = 171;

        return new Body(yearManager, birthYear, height, Gender.MALE);
    }
}
