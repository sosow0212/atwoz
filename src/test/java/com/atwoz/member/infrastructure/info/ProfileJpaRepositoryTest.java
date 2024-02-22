package com.atwoz.member.infrastructure.info;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.SoftAssertions.assertSoftly;

import com.atwoz.member.domain.info.profile.Body;
import com.atwoz.member.domain.info.profile.Job;
import com.atwoz.member.domain.info.profile.Location;
import com.atwoz.member.domain.info.profile.Position;
import com.atwoz.member.domain.info.profile.Profile;
import com.atwoz.member.domain.member.Member;
import com.atwoz.member.fixture.info.BodyFixture;
import com.atwoz.member.fixture.info.JobFixture;
import com.atwoz.member.fixture.info.LocationFixture;
import com.atwoz.member.fixture.info.PositionFixture;
import com.atwoz.member.fixture.member.MemberFixture;
import com.atwoz.member.infrastructure.info.profile.ProfileJpaRepository;
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
public class ProfileJpaRepositoryTest {

    @Autowired
    private ProfileJpaRepository profileJpaRepository;

    @Autowired
    private MemberJpaRepository memberJpaRepository;

    private Member member;

    @BeforeEach
    void init() {
        member = memberJpaRepository.save(MemberFixture.일반_유저_생성());
    }

    @Test
    void profile을_저장한다() {
        // given
        Long memberId = member.getId();
        Body body = BodyFixture.일반_body_생성();
        Location location = LocationFixture.일반_위치_생성();
        Position position = PositionFixture.일반_좌표_생성();
        Job job = JobFixture.일반_직업_생성();

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
        Body body = BodyFixture.일반_body_생성();
        Location location = LocationFixture.일반_위치_생성();
        Position position = PositionFixture.일반_좌표_생성();
        Job job = JobFixture.일반_직업_생성();

        Profile newProfile = new Profile(memberId, body, location, position, job);
        Profile saveProfile = profileJpaRepository.save(newProfile);

        // when
        Optional<Profile> findProfile = profileJpaRepository.findByMemberId(memberId);

        // then
        assertSoftly(softly -> {
            softly.assertThat(findProfile).isPresent();
            softly.assertThat(findProfile.get())
                    .usingRecursiveComparison()
                    .isEqualTo(saveProfile);
        });
    }
}
