package com.atwoz.mission.application.mission.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record MissionCreateRequest(
        @NotBlank(message = "미션의 내용을 입력해주세요.")
        String title,

        @NotBlank(message = "미션의 타입을 입력해주세요.")
        String missionType,

        @NotNull(message = "미션의 보상 하트 수를 입력해주세요.")
        Integer reward,

        @NotBlank(message = "공개 미션인지 입력해주세요.")
        String publicOption
) {
}
