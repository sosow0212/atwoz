package com.atwoz.mission.intrastructure.membermission;

import com.atwoz.mission.domain.membermission.MemberMissionRepository;
import com.atwoz.mission.intrastructure.membermission.dto.MemberMissionSimpleResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

@RequiredArgsConstructor
@Repository
public class MemberMissionRepositoryImpl implements MemberMissionRepository {

    private final MemberMissionQueryRepository memberMissionQueryRepository;

    @Override
    public Page<MemberMissionSimpleResponse> findMemberMissionsWithPaging(final Long memberId, final Pageable pageable) {
        return memberMissionQueryRepository.findMemberMissionsWithPaging(memberId, pageable);
    }

    @Override
    public List<MemberMissionSimpleResponse> findMemberMissionsByStatus(final Long memberId, final boolean isStatusClear) {
        return memberMissionQueryRepository.findMemberMissionsByStatus(memberId, isStatusClear);
    }
}
