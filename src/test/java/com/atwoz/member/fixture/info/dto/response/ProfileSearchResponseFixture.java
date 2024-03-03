package com.atwoz.member.fixture.info.dto.response;

import static com.atwoz.member.fixture.info.dto.response.BodySearchResponseFixture.회원_신체_정보_조회_응답;
import static com.atwoz.member.fixture.info.dto.response.LocationSearchResponseFixture.회원_위치_조회_응답;
import static com.atwoz.member.fixture.info.dto.response.PositionSearchResponseFixture.회원_좌표_조회_응답;

import com.atwoz.member.domain.info.profile.Job;
import com.atwoz.member.ui.info.dto.profile.BodySearchResponse;
import com.atwoz.member.ui.info.dto.profile.LocationSearchResponse;
import com.atwoz.member.ui.info.dto.profile.PositionSearchResponse;
import com.atwoz.member.ui.info.dto.profile.ProfileSearchResponse;

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
