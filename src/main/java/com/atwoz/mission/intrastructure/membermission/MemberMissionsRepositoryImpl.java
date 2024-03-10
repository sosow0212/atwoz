package com.atwoz.mission.intrastructure.membermission;

import com.atwoz.mission.domain.membermission.MemberMissionsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class MemberMissionsRepositoryImpl implements MemberMissionsRepository {

    private final MemberMissionsJpaRepository memberMissionsJpaRepository;
}
