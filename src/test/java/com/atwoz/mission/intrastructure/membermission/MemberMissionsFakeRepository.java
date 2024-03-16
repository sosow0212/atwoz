package com.atwoz.mission.intrastructure.membermission;

import com.atwoz.mission.domain.membermission.MemberMissions;
import com.atwoz.mission.domain.membermission.MemberMissionsRepository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class MemberMissionsFakeRepository implements MemberMissionsRepository {

    private final Map<Long, MemberMissions> map = new HashMap<>();

    private Long id = 0L;

    @Override
    public MemberMissions save(final MemberMissions memberMissions) {
        id++;
        MemberMissions newMemberMissions = MemberMissions.builder()
                .id(id)
                .memberId(memberMissions.getMemberId())
                .memberMissions(memberMissions.getMemberMissions())
                .build();
        map.put(id, newMemberMissions);

        return newMemberMissions;
    }

    @Override
    public Optional<MemberMissions> findByMemberId(final Long memberId) {
        return map.values().stream()
                .filter(memberMissions -> memberId.equals(memberMissions.getMemberId()))
                .findAny();
    }
}
