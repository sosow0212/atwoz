package com.atwoz.mission.fixture;

import com.atwoz.mission.domain.MemberMission;
import com.atwoz.mission.domain.MemberMissions;

import java.util.ArrayList;
import java.util.List;

public class MemberMissionsFixture {

    public static MemberMissions 멤버_미션들_생성(final MemberMission... args) {
        List<MemberMission> memberMissions = new ArrayList<>();
        memberMissions.addAll(List.of(args));

        return MemberMissions.builder()
                .id(1L)
                .memberId(1L)
                .memberMissions(memberMissions)
                .build();
    }
}
