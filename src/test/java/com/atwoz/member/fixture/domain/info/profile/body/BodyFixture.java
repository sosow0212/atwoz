package com.atwoz.member.fixture.domain.info.profile.body;

import com.atwoz.member.domain.info.profile.body.Body;
import com.atwoz.member.domain.info.profile.body.Gender;
import com.atwoz.member.domain.info.profile.body.YearManager;
import com.atwoz.member.infrastructure.info.profile.body.FakeYearManager;

@SuppressWarnings("NonAsciiCharacters")
public class BodyFixture {

    public static Body 회원_일반_body_생성() {
        YearManager yearManager = new FakeYearManager();
        int birthYear = 2000;
        int height = 171;
        Gender gender = Gender.MALE;

        return new Body(yearManager, birthYear, height, gender);
    }

    public static Body 회원_수정_body_생성() {
        YearManager yearManager = new FakeYearManager();
        int birthYear = 2001;
        int height = 170;
        Gender gender = Gender.FEMALE;

        return new Body(yearManager, birthYear, height, gender);
    }
}
