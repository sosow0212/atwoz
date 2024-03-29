package com.atwoz.member.domain.info.profile;

import static com.atwoz.member.fixture.application.info.profile.ProfileRequestFixture.회원_프로필_수정_요청_내부;
import static com.atwoz.member.fixture.domain.info.profile.ProfileFixture.회원_일반_프로필_생성;
import static com.atwoz.member.fixture.domain.info.profile.body.BodyFixture.회원_수정_body_생성;
import static com.atwoz.member.fixture.domain.info.profile.job.JobFixture.회원_수정_직업_생성;
import static com.atwoz.member.fixture.domain.info.profile.location.LocationFixture.회원_수정_위치_생성;
import static com.atwoz.member.fixture.domain.info.profile.position.PositionFixture.회원_수정_좌표_생성;
import static org.assertj.core.api.SoftAssertions.assertSoftly;

import com.atwoz.member.domain.info.profile.body.Body;
import com.atwoz.member.domain.info.profile.dto.InnerProfileUpdateRequest;
import com.atwoz.member.domain.info.profile.job.Job;
import com.atwoz.member.domain.info.profile.location.Location;
import com.atwoz.member.domain.info.profile.position.Position;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@SuppressWarnings("NonAsciiCharacters")
public class ProfileTest {

    @Test
    void 내용_수정_검증() {
        // given
        Profile profile = 회원_일반_프로필_생성();

        Body updateBody = 회원_수정_body_생성();
        Job updateJob = 회원_수정_직업_생성();
        Location updateLocation = 회원_수정_위치_생성();
        Position updatePosition = 회원_수정_좌표_생성();

        InnerProfileUpdateRequest request = 회원_프로필_수정_요청_내부();

        // when
        profile.updateContentsFrom(request);

        // then
        assertSoftly(softly -> {
            softly.assertThat(profile.getBody()).isEqualTo(updateBody);
            softly.assertThat(profile.getJob()).isEqualTo(updateJob);
            softly.assertThat(profile.getLocation()).isEqualTo(updateLocation);
            softly.assertThat(profile.getPosition()).isEqualTo(updatePosition);
        });
    }
}
