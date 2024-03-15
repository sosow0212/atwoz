package com.atwoz.mission.domain.membermission;

import com.atwoz.mission.intrastructure.membermission.dto.MemberMissionSimpleResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface MemberMissionRepository {

    Page<MemberMissionSimpleResponse> findMemberMissionsWithPaging(Long memberId, Pageable pageable);
    List<MemberMissionSimpleResponse> findMemberMissionsByStatus(Long memberId, boolean isStatusClear);
}
