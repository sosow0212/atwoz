package com.atwoz.mission.domain;

import com.atwoz.mission.exception.MissionNotClearException;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static com.atwoz.mission.fixture.MemberMissionFixture.멤버_미션_생성_완료;
import static com.atwoz.mission.fixture.MemberMissionFixture.멤버_미션_생성_진행중;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@SuppressWarnings("NonAsciiCharacters")
class MemberMissionTest {

    @Test
    void 미션을_클리어한다() {
        // given
        MemberMission memberMission = 멤버_미션_생성_진행중();
        boolean beforeStatus = memberMission.isStatusClear();

        // when
        memberMission.clear();

        // then
        assertThat(memberMission.isStatusClear()).isEqualTo(!beforeStatus);
    }

    @Nested
    class 보상을_받는다 {

        @Test
        void 보상을_정상적으로_받는다() {
            // given
            MemberMission memberMission = 멤버_미션_생성_완료();

            // when
            Integer reward = memberMission.getReward();

            // then
            assertThat(reward).isEqualTo(100);
        }

        @Test
        void 미션을_클리어하지_않고_보상을_받으면_예외를_발생시킨다() {
            // given
            MemberMission memberMission = 멤버_미션_생성_진행중();

            // when & then
            assertThatThrownBy(memberMission::getReward)
                    .isInstanceOf(MissionNotClearException.class);
        }
    }
}
