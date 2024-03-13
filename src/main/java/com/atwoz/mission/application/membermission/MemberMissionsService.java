package com.atwoz.mission.application.membermission;

import com.atwoz.mission.domain.membermission.MemberMission;
import com.atwoz.mission.domain.membermission.MemberMissions;
import com.atwoz.mission.domain.membermission.MemberMissionsRepository;
import com.atwoz.mission.domain.mission.Mission;
import com.atwoz.mission.domain.mission.MissionRepository;
import com.atwoz.mission.exception.MissionNotFoundException;
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
    private final MissionRepository missionRepository;

    public void addMemberMission(final Long memberId, final Long missionId) {
        if (memberMissionsRepository.isExistMemberMissions(memberId)) {
            MemberMissions memberMissions = findMemberMissionsByMemberId(memberId);
            addMissionToMemberMissions(missionId, memberMissions);
            return;
        }
        MemberMissions memberMissions = MemberMissions.createWithMemberId(memberId);
        memberMissionsRepository.save(memberMissions);
        addMissionToMemberMissions(missionId, memberMissions);
    }

    private MemberMissions findMemberMissionsByMemberId(final Long memberId) {
        return memberMissionsRepository.findByMemberId(memberId)
                .orElseThrow(MemberMissionsNotFoundException::new);
    }

    private void addMissionToMemberMissions(final Long missionId, final MemberMissions memberMissions) {
        Mission mission = missionRepository.findById(missionId)
                .orElseThrow(MissionNotFoundException::new);
        memberMissions.addMission(MemberMission.createDefault(mission));
    }

    public void clearMemberMission() {
        // 멤버가 미션 클리어한 경우
    }

    public Integer getRewardByMissionId(final Long memberId, final Long missionId) {
        MemberMissions memberMissions = findMemberMissionsByMemberId(memberId);
        return memberMissions.getRewardBy(missionId);
    }

    public Integer getAllClearMissionsRewards(final Long memberId) {
        MemberMissions memberMissions = findMemberMissionsByMemberId(memberId);
        return memberMissions.getTotalClearedReward();
    }
}
