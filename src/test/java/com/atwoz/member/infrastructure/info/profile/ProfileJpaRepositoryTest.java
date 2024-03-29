package com.atwoz.member.infrastructure.info.profile;

import static com.atwoz.member.fixture.domain.info.profile.body.BodyFixture.회원_일반_body_생성;
import static com.atwoz.member.fixture.domain.info.profile.job.JobFixture.회원_일반_직업_생성;
import static com.atwoz.member.fixture.domain.info.profile.location.LocationFixture.회원_일반_위치_생성;
import static com.atwoz.member.fixture.domain.info.profile.position.PositionFixture.회원_일반_좌표_생성;
import static com.atwoz.member.fixture.domain.member.MemberFixture.일반_유저_생성;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.SoftAssertions.assertSoftly;

import com.atwoz.member.domain.info.profile.Profile;
import com.atwoz.member.domain.info.profile.body.Body;
import com.atwoz.member.domain.info.profile.job.Job;
import com.atwoz.member.domain.info.profile.location.Location;
import com.atwoz.member.domain.info.profile.position.Position;
import com.atwoz.member.domain.member.Member;
import com.atwoz.member.infrastructure.member.MemberJpaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import java.util.Optional;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@SuppressWarnings("NonAsciiCharacters")
@DataJpaTest
class ProfileJpaRepositoryTest {

    @Autowired
    private ProfileJpaRepository profileJpaRepository;

    @Autowired
    private MemberJpaRepository memberJpaRepository;

    private Member member;

    @BeforeEach
    void init() {
        member = memberJpaRepository.save(일반_유저_생성());
    }

    @Test
    void profile을_저장한다() {
        // given
        Long memberId = member.getId();
        Body body = 회원_일반_body_생성();
        Location location = 회원_일반_위치_생성();
        Position position = 회원_일반_좌표_생성();
        Job job = 회원_일반_직업_생성();

        Profile newProfile = new Profile(memberId, body, location, position, job);

        // when
        Profile saveProfile = profileJpaRepository.save(newProfile);

        // then
        assertThat(saveProfile)
                .usingRecursiveComparison()
                .ignoringFields("id")
                .isEqualTo(newProfile);
    }

    @Test
    void profile을_조회한다() {
        // given
        Long memberId = member.getId();
        Body body = 회원_일반_body_생성();
        Location location = 회원_일반_위치_생성();
        Position position = 회원_일반_좌표_생성();
        Job job = 회원_일반_직업_생성();

        Profile newProfile = new Profile(memberId, body, location, position, job);
        Profile saveProfile = profileJpaRepository.save(newProfile);

        // when
        Optional<Profile> findProfile = profileJpaRepository.findByMemberId(memberId);

        // then
        assertSoftly(softly -> {
            softly.assertThat(findProfile).isPresent();
            softly.assertThat(findProfile.get())
                    .usingRecursiveComparison()
                    .ignoringFields("id")
                    .isEqualTo(saveProfile);
        });
    }
}
