package com.atwoz.member.fixture.infrastructure.info.profile;

import static com.atwoz.member.fixture.infrastructure.info.profile.body.BodySearchResponseFixture.회원_신체_정보_조회_응답;
import static com.atwoz.member.fixture.infrastructure.info.profile.location.LocationSearchResponseFixture.회원_위치_조회_응답;
import static com.atwoz.member.fixture.infrastructure.info.profile.position.PositionSearchResponseFixture.회원_좌표_조회_응답;

import com.atwoz.member.domain.info.profile.job.Job;
import com.atwoz.member.infrastructure.info.profile.body.dto.BodySearchResponse;
import com.atwoz.member.infrastructure.info.profile.location.dto.LocationSearchResponse;
import com.atwoz.member.infrastructure.info.profile.position.dto.PositionSearchResponse;
import com.atwoz.member.infrastructure.info.profile.dto.ProfileSearchResponse;

@SuppressWarnings("NonAsciiCharacters")
public class ProfileSearchResponseFixture {

    public static ProfileSearchResponse 회원_프로필_조회_응답() {
        BodySearchResponse bodySearchResponse = 회원_신체_정보_조회_응답();
        LocationSearchResponse locationSearchResponse = 회원_위치_조회_응답();
        PositionSearchResponse positionSearchResponse = 회원_좌표_조회_응답();
        String job = Job.RESEARCH_AND_DEVELOP.getCode();

        return new ProfileSearchResponse(
                bodySearchResponse,
                locationSearchResponse,
                positionSearchResponse,
                job
        );
    }
}
