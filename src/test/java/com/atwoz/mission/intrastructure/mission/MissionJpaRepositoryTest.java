package com.atwoz.mission.intrastructure.mission;

import com.atwoz.mission.domain.mission.Mission;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static com.atwoz.mission.fixture.MissionFixture.미션_생성_리워드_100_데일리_공개_id없음;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.SoftAssertions.assertSoftly;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@SuppressWarnings("NonAsciiCharacters")
@DataJpaTest
class MissionJpaRepositoryTest {

    @Autowired
    private MissionJpaRepository missionJpaRepository;

    @Test
    void 미션_생성() {
        // given
        Mission mission = 미션_생성_리워드_100_데일리_공개_id없음();

        // when
        Mission result = missionJpaRepository.save(mission);

        // then
        assertThat(result).usingRecursiveComparison()
                .ignoringFields("id")
                .ignoringFields("createdAt")
                .isEqualTo(mission);
    }

    @Test
    void 미션_조회() {
        // given
        Mission mission = missionJpaRepository.save(미션_생성_리워드_100_데일리_공개_id없음());

        // when
        Optional<Mission> result = missionJpaRepository.findById(mission.getId());

        // then
        assertSoftly(softly -> {
            softly.assertThat(result).isPresent();
            softly.assertThat(result.get())
                    .usingRecursiveComparison()
                    .isEqualTo(mission);
        });
    }

    @Test
    void 미션_삭제() {
        // given
        Mission mission = missionJpaRepository.save(미션_생성_리워드_100_데일리_공개_id없음());

        // when
        missionJpaRepository.deleteById(mission.getId());

        // then
        Optional<Mission> result = missionJpaRepository.findById(mission.getId());
        assertThat(result).isEmpty();
    }
}
