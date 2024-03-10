package com.atwoz.mission.intrastructure.membermission;

import com.atwoz.mission.domain.membermission.MemberMission;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberMissionsJpaRepository extends JpaRepository<MemberMission, Long> {
}
