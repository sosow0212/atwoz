package com.atwoz.member.fixture.info.dto.request;

import static com.atwoz.member.fixture.info.dto.request.LocationUpdateRequestFixture.위치_수정_요청;
import static com.atwoz.member.fixture.info.dto.request.PositionUpdateRequestFixture.좌표_수정_요청;

import com.atwoz.member.application.info.dto.profile.LocationUpdateRequest;
import com.atwoz.member.application.info.dto.profile.PositionUpdateRequest;
import com.atwoz.member.application.info.dto.profile.ProfileUpdateRequest;
import com.atwoz.member.domain.info.profile.Gender;
import com.atwoz.member.domain.info.profile.Job;

@SuppressWarnings("NonAsciiCharacters")
public class ProfileUpdateRequestFixture {

    public static ProfileUpdateRequest 프로필_수정_요청() {
        int birthYear = 2001;
        int height = 170;
        String gender = Gender.FEMALE.getName();
        LocationUpdateRequest locationUpdateRequest = 위치_수정_요청();
        PositionUpdateRequest positionUpdateRequest = 좌표_수정_요청();
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
