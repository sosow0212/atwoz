package com.atwoz.member.fixture.domain.info.profile;

import static com.atwoz.member.fixture.domain.info.profile.body.BodyFixture.회원_수정_body_생성;
import static com.atwoz.member.fixture.domain.info.profile.body.BodyFixture.회원_일반_body_생성;
import static com.atwoz.member.fixture.domain.info.profile.job.JobFixture.회원_수정_직업_생성;
import static com.atwoz.member.fixture.domain.info.profile.job.JobFixture.회원_일반_직업_생성;
import static com.atwoz.member.fixture.domain.info.profile.location.LocationFixture.회원_수정_위치_생성;
import static com.atwoz.member.fixture.domain.info.profile.location.LocationFixture.회원_일반_위치_생성;
import static com.atwoz.member.fixture.domain.info.profile.position.PositionFixture.회원_수정_좌표_생성;
import static com.atwoz.member.fixture.domain.info.profile.position.PositionFixture.회원_일반_좌표_생성;

import com.atwoz.member.domain.info.profile.body.Body;
import com.atwoz.member.domain.info.profile.job.Job;
import com.atwoz.member.domain.info.profile.location.Location;
import com.atwoz.member.domain.info.profile.position.Position;
import com.atwoz.member.domain.info.profile.Profile;

@SuppressWarnings("NonAsciiCharacters")
public class ProfileFixture {

    public static Profile 회원_일반_프로필_생성() {
        Long memberId = 1L;
        Body body = 회원_일반_body_생성();
        Job job = 회원_일반_직업_생성();
        Location location = 회원_일반_위치_생성();
        Position position = 회원_일반_좌표_생성();

        return new Profile(memberId, body, location, position, job);
    }

    public static Profile 회원_수정_프로필_생성() {
        Long memberId = 1L;
        Body body = 회원_수정_body_생성();
        Job job = 회원_수정_직업_생성();
        Location location = 회원_수정_위치_생성();
        Position position = 회원_수정_좌표_생성();

        return new Profile(memberId, body, location, position, job);
    }
}
