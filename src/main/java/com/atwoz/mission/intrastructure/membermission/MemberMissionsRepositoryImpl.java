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
    public void save(final MemberMissions memberMissions) {
        memberMissionsJpaRepository.save(memberMissions);
    }

    @Override
    public Optional<MemberMissions> findByMemberId(final Long memberId) {
        return memberMissionsJpaRepository.findByMemberId(memberId);
    }

    @Override
    public boolean isExistMemberMissions(final Long memberId) {
        return memberMissionsJpaRepository.existsByMemberId(memberId);
    }
}
