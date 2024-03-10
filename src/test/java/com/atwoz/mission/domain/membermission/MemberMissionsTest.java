package com.atwoz.mission.domain.membermission;

import com.atwoz.mission.domain.membermission.MemberMission;
import com.atwoz.mission.domain.membermission.MemberMissions;
import com.atwoz.mission.exception.MissionNotFoundException;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static com.atwoz.mission.fixture.MemberMissionFixture.멤버_미션_생성_완료;
import static com.atwoz.mission.fixture.MemberMissionFixture.멤버_미션_생성_진행중;
import static com.atwoz.mission.fixture.MemberMissionsFixture.멤버_미션들_생성;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@SuppressWarnings("NonAsciiCharacters")
class MemberMissionsTest {

    @Nested
    class 개별적으로_미션_보상_받기 {

        @Test
        void 미션_id로_보상을_정상적으로_받는다() {
            // given
            MemberMission memberMission = 멤버_미션_생성_완료();
            MemberMissions memberMissions = 멤버_미션들_생성(memberMission);

            // when
            Integer reward = memberMissions.getRewardBy(memberMission.getId());

            // then
            assertThat(reward).isEqualTo(memberMission.getReward());
        }

        @Test
        void 미션_id가_존재하지_않으면_보상을_받을_수_없다_예외를_발생한다() {
            // given
            MemberMission memberMission = 멤버_미션_생성_완료();
            MemberMissions memberMissions = 멤버_미션들_생성(memberMission);

            // when & then
            assertThatThrownBy(() -> memberMissions.getRewardBy(-1L))
                    .isInstanceOf(MissionNotFoundException.class);
        }
    }

    @Test
    void 완료한_미션의_보상을_모두_받는다() {
        // given
        MemberMissions memberMissions = 멤버_미션들_생성(멤버_미션_생성_완료(), 멤버_미션_생성_진행중());

        // when
        Integer result = memberMissions.getTotalClearedReward();

        // then
        assertThat(result).isEqualTo(멤버_미션_생성_완료().getReward());
    }

    @Test
    void 미션을_추가한다() {
        // given
        MemberMissions memberMissions = 멤버_미션들_생성(멤버_미션_생성_완료(), 멤버_미션_생성_진행중());
        int beforeSize = memberMissions.getMemberMissions().size();

        // when
        memberMissions.addMission(멤버_미션_생성_완료());

        // then
        assertThat(memberMissions.getMemberMissions().size()).isEqualTo(beforeSize + 1);
    }
}
