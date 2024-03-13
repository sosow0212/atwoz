package com.atwoz.mission.domain.membermission;

import java.util.Optional;

public interface MemberMissionsRepository {

    Optional<MemberMissions> findByMemberId(Long memberId);
}
