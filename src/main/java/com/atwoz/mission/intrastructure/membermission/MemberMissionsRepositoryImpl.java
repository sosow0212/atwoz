package com.atwoz.mission.intrastructure.membermission;

import com.atwoz.mission.domain.membermission.MemberMissions;
import com.atwoz.mission.domain.membermission.MemberMissionsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class MemberMissionsRepositoryImpl implements MemberMissionsRepository {

    private final MemberMissionsJpaRepository memberMissionsJpaRepository;

    @Override
    public Optional<MemberMissions> findByMemberId(final Long memberId) {
        return memberMissionsJpaRepository.findByMemberId(memberId);
    }
}
