package com.atwoz.mission.application.mission;

import com.atwoz.mission.domain.mission.dto.MissionSimpleResponse;
import com.atwoz.mission.domain.mission.Mission;
import com.atwoz.mission.domain.mission.MissionRepository;
import com.atwoz.mission.exception.MissionNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class MissionQueryService {

    private final MissionRepository missionRepository;

    public Page<MissionSimpleResponse> findMissions(final Pageable pageable) {
        return missionRepository.findAllBoardWithPaging(pageable);
    }

    public Mission findMissionDetail(final Long missionId) {
        return missionRepository.findById(missionId)
                .orElseThrow(MissionNotFoundException::new);
    }
}
