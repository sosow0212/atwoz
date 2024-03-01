package com.atwoz.member.fixture.info.dto.request;

import static com.atwoz.member.fixture.info.dto.request.LocationRequestFixture.회원_위치_생성_요청;
import static com.atwoz.member.fixture.info.dto.request.LocationRequestFixture.회원_위치_수정_요청;
import static com.atwoz.member.fixture.info.dto.request.PositionRequestFixture.회원_좌표_생성_요청;
import static com.atwoz.member.fixture.info.dto.request.PositionRequestFixture.회원_좌표_수정_요청;

import com.atwoz.member.application.info.dto.profile.ProfileUpdateRequest;
import com.atwoz.member.application.info.dto.profile.ProfileWriteRequest;
import com.atwoz.member.application.info.dto.profile.location.LocationUpdateRequest;
import com.atwoz.member.application.info.dto.profile.location.LocationWriteRequest;
import com.atwoz.member.application.info.dto.profile.position.PositionUpdateRequest;
import com.atwoz.member.application.info.dto.profile.position.PositionWriteRequest;
import com.atwoz.member.domain.info.profile.Gender;
import com.atwoz.member.domain.info.profile.Job;

@SuppressWarnings("NonAsciiCharacters")
public class ProfileRequestFixture {

    public static ProfileWriteRequest 회원_프로필_생성_요청() {
        int birthYear = 2000;
        int height = 171;
        String gender = Gender.MALE.getName();
        LocationWriteRequest locationWriteRequest = 회원_위치_생성_요청();
        PositionWriteRequest positionWriteRequest = 회원_좌표_생성_요청();
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

    public static ProfileUpdateRequest 회원_프로필_수정_요청() {
        int birthYear = 2001;
        int height = 170;
        String gender = Gender.FEMALE.getName();
        LocationUpdateRequest locationUpdateRequest = 회원_위치_수정_요청();
        PositionUpdateRequest positionUpdateRequest = 회원_좌표_수정_요청();
        String job = Job.LAW.getCode();

        return new ProfileUpdateRequest(
                birthYear,
                height,
                gender,
                locationUpdateRequest,
                positionUpdateRequest,
                job
        );
    }
}
