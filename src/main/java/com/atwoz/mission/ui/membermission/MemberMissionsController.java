package com.atwoz.mission.ui.membermission;

import com.atwoz.member.ui.auth.support.auth.AuthMember;
import com.atwoz.mission.application.membermission.MemberMissionsQueryService;
import com.atwoz.mission.application.membermission.MemberMissionsService;
import com.atwoz.mission.ui.membermission.dto.RewardResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    @GetMapping("/clear")
    public ResponseEntity<Void> findMissionsByStatus(@RequestParam("status") final Boolean isStatusClear) {
        memberMissionsQueryService.findMissionsByStatus(isStatusClear);
        return null;
    }

    @PatchMapping("/{missionId}")
    public ResponseEntity<Void> clearMission(@AuthMember final Long memberId, @PathVariable final Long missionId) {
        memberMissionsService.clearMemberMission(memberId, missionId);
        return ResponseEntity.ok()
                .build();
    }

    @PostMapping("/{missionId}")
    public ResponseEntity<Void> addMemberMission(@AuthMember final Long memberId, @PathVariable final Long missionId) {
        memberMissionsService.addMemberMission(memberId, missionId);
        return ResponseEntity.status(HttpStatus.CREATED)
                .build();
    }

    @GetMapping("/{missionId}")
    public ResponseEntity<RewardResponse> getRewardByMissionId(@AuthMember final Long memberId, @PathVariable final Long missionId) {
        Integer reward = memberMissionsService.getRewardByMissionId(memberId, missionId);
        return ResponseEntity.ok()
                .body(new RewardResponse(reward));
    }

    @GetMapping("/all")
    public ResponseEntity<RewardResponse> getAllRewards(@AuthMember final Long memberId) {
        Integer reward = memberMissionsService.getAllClearMissionsRewards(memberId);
        return ResponseEntity.ok()
                .body(new RewardResponse(reward));
    }
}
