package com.atwoz.mission.ui;

import com.atwoz.mission.application.mission.MissionQueryService;
import com.atwoz.mission.application.mission.MissionService;
import com.atwoz.mission.application.mission.dto.MissionCreateRequest;
import com.atwoz.mission.domain.mission.Mission;
import com.atwoz.mission.domain.mission.dto.MissionDetailResponse;
import com.atwoz.mission.domain.mission.dto.MissionPagingResponse;
import com.atwoz.mission.domain.mission.dto.MissionSimpleResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

import static org.springframework.data.domain.Sort.Direction.DESC;

@RequiredArgsConstructor
@RequestMapping("/api/missions")
@RestController
public class MissionController {

    private final MissionService missionService;
    private final MissionQueryService missionQueryService;

    @PostMapping
    public ResponseEntity<Long> createNewMission(@RequestBody final MissionCreateRequest request) {
        Long missionId = missionService.createNewMission(request);
        return ResponseEntity.created(URI.create("/api/missions/" + missionId))
                .build();
    }

    @DeleteMapping("/{missionId}")
    public ResponseEntity<Void> deleteMissionById(@PathVariable final Long missionId) {
        missionService.deleteMissionById(missionId);
        return ResponseEntity.noContent()
                .build();
    }

    @GetMapping
    public ResponseEntity<MissionPagingResponse> findAllMissionsWithPaging(@PageableDefault(sort = "id", direction = DESC) final Pageable pageable) {
        return ResponseEntity.ok(missionQueryService.findMissions(pageable));
    }


    @GetMapping("/{missionId}")
    public ResponseEntity<MissionDetailResponse> findMissionById(@PathVariable final Long missionId) {
        Mission mission = missionQueryService.findMissionDetail(missionId);
        return ResponseEntity.ok(MissionDetailResponse.from(mission));
    }
}
