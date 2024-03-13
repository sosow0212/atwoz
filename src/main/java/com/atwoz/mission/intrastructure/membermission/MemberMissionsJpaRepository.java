package com.atwoz.mission.intrastructure.membermission;

import com.atwoz.mission.domain.membermission.MemberMissions;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface MemberMissionsJpaRepository extends JpaRepository<MemberMissions, Long> {

    Optional<MemberMissions> findByMemberId(final Long memberId);
}
