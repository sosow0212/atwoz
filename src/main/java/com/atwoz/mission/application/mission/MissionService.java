package com.atwoz.mission.application.mission;

import com.atwoz.mission.application.mission.dto.MissionCreateRequest;
import com.atwoz.mission.domain.mission.Mission;
import com.atwoz.mission.domain.mission.MissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
@Service
public class MissionService {

    private final MissionRepository missionRepository;

    public Long createNewMission(final MissionCreateRequest request) {
        Mission mission = Mission.createDefaultRule(
                request.title(),
                request.reward(),
                request.missionType(),
                request.publicOption()
        );

        return missionRepository.save(mission)
                .getId();
    }

    public void deleteMissionById(final Long missionId) {
        missionRepository.deleteById(missionId);
    }
}
