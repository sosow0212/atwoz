package com.atwoz.mission.application.membermission;

import com.atwoz.mission.domain.membermission.MemberMissionsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
@Service
public class MemberMissionsService {

    // TODO : MemberMissionsService 완성하기

    private final MemberMissionsRepository memberMissionsRepository;

    public void addMemberMission() {

    }

    public void clearMemberMission() {
        // 멤버가 미션 클리어한 경우
    }

    public void getRewardByMissionId() {

    }

    public void getAllClearMissionsRewards() {

    }
}
