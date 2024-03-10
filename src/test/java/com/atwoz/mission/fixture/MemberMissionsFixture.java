package com.atwoz.mission.fixture;

import com.atwoz.mission.domain.membermission.MemberMission;
import com.atwoz.mission.domain.membermission.MemberMissions;

import java.util.ArrayList;
import java.util.List;

public class MemberMissionsFixture {

    public static MemberMissions 멤버_미션들_생성(final MemberMission... givenMemberMissions) {
        List<MemberMission> memberMissions = new ArrayList<>(List.of(givenMemberMissions));

        return MemberMissions.builder()
                .id(1L)
                .memberId(1L)
                .memberMissions(memberMissions)
                .build();
    }
}
