package com.atwoz.mission.domain.mission.dto;

import com.atwoz.mission.domain.mission.Mission;

import java.time.LocalDateTime;

public record MissionDetailResponse(
        Long id,
        String title,
        String missionType,
        Integer reward,
        String isPublic,
        LocalDateTime createdDate
) {

    public static MissionDetailResponse from(final Mission mission) {
        return new MissionDetailResponse(
                mission.getId(),
                mission.getTitle(),
                mission.getMissionType().getValue(),
                mission.getReward(),
                mission.getPublicOption().getValue(),
                mission.getCreatedAt()
        );
    }
}
