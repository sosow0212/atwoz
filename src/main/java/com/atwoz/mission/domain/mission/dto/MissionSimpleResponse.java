package com.atwoz.mission.domain.mission.dto;

import com.atwoz.mission.domain.mission.vo.MissionType;
import com.atwoz.mission.domain.mission.vo.PublicOption;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;

public record MissionSimpleResponse(
        Long id,
        String title,
        MissionType missionType,
        Integer reward,
        PublicOption publicOption,
        LocalDateTime createdDate
) {

    public String getMissionType() {
        return missionType.getValue();
    }

    public String getPublicOption() {
        return publicOption.getValue();
    }
}
