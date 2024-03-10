package com.atwoz.mission.domain.mission;

import com.atwoz.mission.domain.mission.dto.MissionSimpleResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface MissionRepository {

    Optional<Mission> findById(Long missionId);

    Page<MissionSimpleResponse> findAllBoardWithPaging(Pageable pageable);

    Mission save(Mission mission);

    void deleteById(Long missionId);
}
