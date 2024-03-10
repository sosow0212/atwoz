package com.atwoz.mission.application.membermission;

import com.atwoz.mission.domain.membermission.MemberMissionsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class MemberMissionsQueryService {

    // TODO : MemberMissionsQueryService 완성하기

    private final MemberMissionsRepository memberMissionsRepository;

    public void findMemberMissionsWithPaging() {

    }

    public void findNotClearMissions() {

    }

    public void findClearMissions() {

    }
}
