package com.atwoz.mission.domain.mission.dto;

import com.atwoz.mission.domain.mission.vo.MissionType;
import com.atwoz.mission.domain.mission.vo.PublicOption;

import java.time.LocalDateTime;

public record MissionSimpleResponse(
        Long id,
        String title,
        MissionType missionType,
        Integer reward,
        PublicOption isPublic,
        LocalDateTime createdDate
) {
}
