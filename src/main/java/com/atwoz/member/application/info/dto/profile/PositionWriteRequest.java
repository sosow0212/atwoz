package com.atwoz.member.application.info.dto.profile;

import jakarta.validation.constraints.NotNull;

public record PositionWriteRequest(
        @NotNull(message = "위도가 있어야 합니다.")
        Double latitude,

        @NotNull(message = "경도가 있어야 합니다.")
        Double longitude
){
}
