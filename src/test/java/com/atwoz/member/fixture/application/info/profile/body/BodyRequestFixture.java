package com.atwoz.member.fixture.application.info.profile.body;

import com.atwoz.member.application.info.profile.body.dto.BodyUpdateRequest;
import com.atwoz.member.application.info.profile.body.dto.BodyWriteRequest;
import com.atwoz.member.domain.info.profile.body.Gender;

@SuppressWarnings("NonAsciiCharacters")
public class BodyRequestFixture {

    public static BodyWriteRequest 회원_신체_정보_생성_요청() {
        int birthYear = 2000;
        int height = 171;
        String gender = Gender.MALE.getName();

        return new BodyWriteRequest(birthYear, height, gender);
    }

    public static BodyUpdateRequest 회원_신체_정보_수정_요청() {
        int birthYear = 2001;
        int height = 170;
        String gender = Gender.FEMALE.getName();

        return new BodyUpdateRequest(birthYear, height, gender);
    }
}
