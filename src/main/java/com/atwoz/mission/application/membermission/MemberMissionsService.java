package com.atwoz.mission.application.membermission;

import com.atwoz.mission.domain.membermission.MemberMissions;
import com.atwoz.mission.domain.membermission.MemberMissionsRepository;
import com.atwoz.mission.exception.membermissions.exceptions.MemberMissionsNotFoundException;
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

    public Integer getRewardByMissionId(final Long memberId, final Long missionId) {
        MemberMissions memberMissions = findMemberMissionsByMemberId(memberId);
        return memberMissions.getRewardBy(missionId);
    }

    private MemberMissions findMemberMissionsByMemberId(final Long memberId) {
        return memberMissionsRepository.findByMemberId(memberId)
                .orElseThrow(MemberMissionsNotFoundException::new);
    }

    public Integer getAllClearMissionsRewards(final Long memberId) {
        MemberMissions memberMissions = findMemberMissionsByMemberId(memberId);
        return memberMissions.getTotalClearedReward();
    }
}
