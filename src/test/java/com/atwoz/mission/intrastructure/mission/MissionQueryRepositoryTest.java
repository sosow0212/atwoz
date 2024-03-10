package com.atwoz.mission.intrastructure.mission;

import com.atwoz.helper.IntegrationHelper;
import com.atwoz.mission.domain.mission.Mission;
import com.atwoz.mission.domain.mission.dto.MissionSimpleResponse;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static com.atwoz.mission.fixture.MissionFixture.미션_생성_리워드_100_데일리_공개_id없음;
import static org.assertj.core.api.SoftAssertions.assertSoftly;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@SuppressWarnings("NonAsciiCharacters")
class MissionQueryRepositoryTest extends IntegrationHelper {

    @Autowired
    private MissionJpaRepository missionJpaRepository;

    @Autowired
    private MissionQueryRepository missionQueryRepository;

    @Test
    void 페이징_조회() {
        // given
        List<Mission> missions = new ArrayList<>();

        for (int i = 0; i < 15; i++) {
            missions.add(missionJpaRepository.save(미션_생성_리워드_100_데일리_공개_id없음()));
        }

        PageRequest pageRequest = PageRequest.of(0, 10);

        // when
        Page<MissionSimpleResponse> found = missionQueryRepository.findAllMissionsWithPaging(pageRequest);

        // then
        List<MissionSimpleResponse> expected = missions.stream()
                .sorted(Comparator.comparing(Mission::getId).reversed())
                .limit(10)
                .map(it -> new MissionSimpleResponse(it.getId(), it.getTitle(), it.getMissionType(), it.getReward(), it.getPublicOption(), it.getCreatedAt()))
                .toList();

        assertSoftly(softly -> {
            softly.assertThat(found).hasSize(10);
            softly.assertThat(found.hasNext()).isTrue();
            softly.assertThat(found.getContent())
                    .usingRecursiveComparison()
                    .ignoringFieldsOfTypes(LocalDateTime.class)
                    .isEqualTo(expected);
        });
    }
}
