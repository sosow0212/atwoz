package com.atwoz.member.domain.info.profile;

import static com.atwoz.member.fixture.info.BodyFixture.수정_body_생성;
import static com.atwoz.member.fixture.info.BodyFixture.일반_body_생성;
import static com.atwoz.member.fixture.info.JobFixture.수정_직업_생성;
import static com.atwoz.member.fixture.info.JobFixture.일반_직업_생성;
import static com.atwoz.member.fixture.info.LocationFixture.수정_위치_생성;
import static com.atwoz.member.fixture.info.LocationFixture.일반_위치_생성;
import static com.atwoz.member.fixture.info.PositionFixture.수정_좌표_생성;
import static com.atwoz.member.fixture.info.PositionFixture.일반_좌표_생성;
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
        Body body = 일반_body_생성();
        Job job = 일반_직업_생성();
        Location location = 일반_위치_생성();
        Position position = 일반_좌표_생성();
        Profile profile = new Profile(memberId, body, location, position, job);

        Body updateBody = 수정_body_생성();
        Job updateJob = 수정_직업_생성();
        Location updateLocation = 수정_위치_생성();
        Position updatePosition = 수정_좌표_생성();

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
