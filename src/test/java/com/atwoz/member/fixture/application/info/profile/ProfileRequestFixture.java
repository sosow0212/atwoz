package com.atwoz.member.fixture.application.info.profile;

import static com.atwoz.member.fixture.application.info.profile.body.BodyRequestFixture.회원_신체_정보_생성_요청;
import static com.atwoz.member.fixture.application.info.profile.body.BodyRequestFixture.회원_신체_정보_수정_요청;
import static com.atwoz.member.fixture.application.info.profile.location.LocationRequestFixture.회원_위치_생성_요청;
import static com.atwoz.member.fixture.application.info.profile.location.LocationRequestFixture.회원_위치_수정_요청;
import static com.atwoz.member.fixture.application.info.profile.position.PositionRequestFixture.회원_좌표_생성_요청;
import static com.atwoz.member.fixture.application.info.profile.position.PositionRequestFixture.회원_좌표_수정_요청;

import com.atwoz.member.application.info.profile.dto.ProfileUpdateRequest;
import com.atwoz.member.application.info.profile.dto.ProfileWriteRequest;
import com.atwoz.member.application.info.profile.body.dto.BodyUpdateRequest;
import com.atwoz.member.application.info.profile.body.dto.BodyWriteRequest;
import com.atwoz.member.application.info.profile.location.dto.LocationUpdateRequest;
import com.atwoz.member.application.info.profile.location.dto.LocationWriteRequest;
import com.atwoz.member.application.info.profile.position.dto.PositionUpdateRequest;
import com.atwoz.member.application.info.profile.position.dto.PositionWriteRequest;
import com.atwoz.member.domain.info.profile.job.Job;

@SuppressWarnings("NonAsciiCharacters")
public class ProfileRequestFixture {

    public static ProfileWriteRequest 회원_프로필_생성_요청() {
        BodyWriteRequest bodyWriteRequest = 회원_신체_정보_생성_요청();
        LocationWriteRequest locationWriteRequest = 회원_위치_생성_요청();
        PositionWriteRequest positionWriteRequest = 회원_좌표_생성_요청();
        String job = Job.RESEARCH_AND_DEVELOP.getCode();

        return new ProfileWriteRequest(
                bodyWriteRequest,
                locationWriteRequest,
                positionWriteRequest,
                job
        );
    }

    public static ProfileUpdateRequest 회원_프로필_수정_요청() {
        BodyUpdateRequest bodyUpdateRequest = 회원_신체_정보_수정_요청();
        LocationUpdateRequest locationUpdateRequest = 회원_위치_수정_요청();
        PositionUpdateRequest positionUpdateRequest = 회원_좌표_수정_요청();
        String job = Job.LAW.getCode();

        return new ProfileUpdateRequest(
                bodyUpdateRequest,
                locationUpdateRequest,
                positionUpdateRequest,
                job
        );
    }
}
