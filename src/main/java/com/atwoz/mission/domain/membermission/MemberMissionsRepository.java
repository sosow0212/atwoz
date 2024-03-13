package com.atwoz.mission.domain.membermission;

import java.util.Optional;

public interface MemberMissionsRepository {

    void save(MemberMissions memberMissions);
    Optional<MemberMissions> findByMemberId(Long memberId);
    boolean isExistMemberMissions(Long memberId);
}
