package com.atwoz.mission.application.mission.dto;

public record MissionCreateRequest(
        String title,
        String missionType,
        Integer reward,
        String publicOption
) {
}
