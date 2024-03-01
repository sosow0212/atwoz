package com.atwoz.member.fixture.info;

import static com.atwoz.member.fixture.info.BodyFixture.회원_일반_body_생성;
import static com.atwoz.member.fixture.info.JobFixture.회원_일반_직업_생성;
import static com.atwoz.member.fixture.info.LocationFixture.회원_일반_위치_생성;
import static com.atwoz.member.fixture.info.PositionFixture.회원_일반_좌표_생성;

import com.atwoz.member.domain.info.profile.Body;
import com.atwoz.member.domain.info.profile.Job;
import com.atwoz.member.domain.info.profile.Location;
import com.atwoz.member.domain.info.profile.Position;
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
}
