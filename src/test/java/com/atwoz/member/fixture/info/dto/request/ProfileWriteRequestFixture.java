package com.atwoz.member.fixture.info.dto.request;

import static com.atwoz.member.fixture.info.dto.request.LocationWriteRequestFixture.위치_생성_요청;
import static com.atwoz.member.fixture.info.dto.request.PositionWriteRequestFixture.좌표_생성_요청;

import com.atwoz.member.application.info.dto.profile.location.LocationWriteRequest;
import com.atwoz.member.application.info.dto.profile.position.PositionWriteRequest;
import com.atwoz.member.application.info.dto.profile.ProfileWriteRequest;
import com.atwoz.member.domain.info.profile.Gender;
import com.atwoz.member.domain.info.profile.Job;

@SuppressWarnings("NonAsciiCharacters")
public class ProfileWriteRequestFixture {

    public static ProfileWriteRequest 프로필_생성_요청() {
        int birthYear = 2000;
        int height = 171;
        String gender = Gender.MALE.getName();
        LocationWriteRequest locationWriteRequest = 위치_생성_요청();
        PositionWriteRequest positionWriteRequest = 좌표_생성_요청();
        String job = Job.RESEARCH_AND_DEVELOP.getCode();

        return new ProfileWriteRequest(
                birthYear,
                height,
                gender,
                locationWriteRequest,
                positionWriteRequest,
                job
        );
    }
}
