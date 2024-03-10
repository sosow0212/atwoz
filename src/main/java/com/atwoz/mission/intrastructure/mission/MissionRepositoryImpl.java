package com.atwoz.mission.intrastructure.mission;

import com.atwoz.mission.domain.mission.Mission;
import com.atwoz.mission.domain.mission.MissionRepository;
import com.atwoz.mission.domain.mission.dto.MissionSimpleResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class MissionRepositoryImpl implements MissionRepository {

    private final MissionJpaRepository missionJpaRepository;
    private final MissionQueryRepository missionQueryRepository;

    @Override
    public Optional<Mission> findById(final Long missionId) {
        return missionJpaRepository.findById(missionId);
    }

    @Override
    public Page<MissionSimpleResponse> findAllBoardWithPaging(final Pageable pageable) {
        return missionQueryRepository.findAllMissionsWithPaging(pageable);
    }

    @Override
    public Mission save(final Mission mission) {
        return missionJpaRepository.save(mission);
    }

    @Override
    public void deleteById(final Long missionId) {
        missionJpaRepository.deleteById(missionId);
    }
}
