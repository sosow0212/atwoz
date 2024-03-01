package com.atwoz.member.domain.info.profile;

import static com.atwoz.member.fixture.info.BodyFixture.회원_수정_body_생성;
import static com.atwoz.member.fixture.info.BodyFixture.회원_일반_body_생성;
import static com.atwoz.member.fixture.info.JobFixture.회원_수정_직업_생성;
import static com.atwoz.member.fixture.info.JobFixture.회원_일반_직업_생성;
import static com.atwoz.member.fixture.info.LocationFixture.회원_수정_위치_생성;
import static com.atwoz.member.fixture.info.LocationFixture.회원_일반_위치_생성;
import static com.atwoz.member.fixture.info.PositionFixture.회원_수정_좌표_생성;
import static com.atwoz.member.fixture.info.PositionFixture.회원_일반_좌표_생성;
import static org.assertj.core.api.SoftAssertions.assertSoftly;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@SuppressWarnings("NonAsciiCharacters")
public class ProfileTest {

    @Test
    void 내용_수정_검증() {
        // given
        Long memberId = 1L;
        Body body = 회원_일반_body_생성();
        Job job = 회원_일반_직업_생성();
        Location location = 회원_일반_위치_생성();
        Position position = 회원_일반_좌표_생성();
        Profile profile = new Profile(memberId, body, location, position, job);

        Body updateBody = 회원_수정_body_생성();
        Job updateJob = 회원_수정_직업_생성();
        Location updateLocation = 회원_수정_위치_생성();
        Position updatePosition = 회원_수정_좌표_생성();

        // when
        profile.updateContents(updateBody, updateLocation, updatePosition, updateJob);

        // then
        assertSoftly(softly -> {
            softly.assertThat(profile.getBody()).isEqualTo(updateBody);
            softly.assertThat(profile.getJob()).isEqualTo(updateJob);
            softly.assertThat(profile.getLocation()).isEqualTo(updateLocation);
            softly.assertThat(profile.getPosition()).isEqualTo(updatePosition);
        });
    }
}
