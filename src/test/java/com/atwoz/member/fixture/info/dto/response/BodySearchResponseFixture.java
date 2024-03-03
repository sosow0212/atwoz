package com.atwoz.member.fixture.info.dto.response;

import com.atwoz.member.domain.info.profile.body.Gender;
import com.atwoz.member.infrastructure.info.profile.body.dto.BodySearchResponse;

@SuppressWarnings("NonAsciiCharacters")
public class BodySearchResponseFixture {

    public static BodySearchResponse 회원_신체_정보_조회_응답() {
        int age = 24;
        int height = 171;
        String gender = Gender.MALE.getName();

        return new BodySearchResponse(age, height, gender);
    }
}
