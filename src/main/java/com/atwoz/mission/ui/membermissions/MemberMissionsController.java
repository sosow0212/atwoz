package com.atwoz.mission.ui.membermissions;

import com.atwoz.mission.application.membermission.MemberMissionsQueryService;
import com.atwoz.mission.application.membermission.MemberMissionsService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/api/members/me/missions")
@RestController
public class MemberMissionsController {

    private final MemberMissionsService memberMissionsService;
    private final MemberMissionsQueryService memberMissionsQueryService;

    @GetMapping
    public ResponseEntity<Void> findMemberMissionsWithPaging(final Pageable pageable) {
        return null;
    }

    @GetMapping
    public ResponseEntity<Void> findNotClearMissions(@RequestParam("status") final Boolean isStatusClear) {
        memberMissionsQueryService.findMissionsByStatus(isStatusClear);
        return null;
    }

    @PostMapping
    public ResponseEntity<Void> clearMission() {
        return null;
    }

    @GetMapping("/{missionId}")
    public ResponseEntity<Void> getRewardByMissionId() {
        return null;
    }

    @GetMapping("/all")
    public ResponseEntity<Void> getAllRewards() {
        return null;
    }
}
