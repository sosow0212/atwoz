package com.atwoz.mission.intrastructure.mission;

import com.atwoz.mission.domain.mission.Mission;
import com.atwoz.mission.domain.mission.MissionRepository;
import com.atwoz.mission.domain.mission.dto.MissionSimpleResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class MissionFakeRepository implements MissionRepository {

    private final Map<Long, Mission> map = new HashMap<>();

    private Long id = 0L;

    @Override
    public Optional<Mission> findById(final Long missionId) {
        return map.values().stream()
                .filter(it -> it.getId().equals(id))
                .findAny();
    }

    @Override
    public Page<MissionSimpleResponse> findAllBoardWithPaging(final Pageable pageable) {
        List<MissionSimpleResponse> expected = map.values().stream()
                .sorted(Comparator.comparing(Mission::getId).reversed())
                .limit(10)
                .map(it -> new MissionSimpleResponse(it.getId(), it.getTitle(), it.getMissionType(), it.getReward(), it.getPublicOption(), it.getCreatedAt()))
                .toList();

        return new PageImpl<>(expected);
    }

    @Override
    public Mission save(final Mission mission) {
        id++;

        Mission savedMission = Mission.builder()
                .id(id)
                .title(mission.getTitle())
                .reward(mission.getReward())
                .missionType(mission.getMissionType())
                .publicOption(mission.getPublicOption())
                .build();

        map.put(id, savedMission);
        return savedMission;
    }

    @Override
    public void deleteById(final Long missionId) {
        map.remove(missionId);
    }
}
