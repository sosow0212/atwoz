package com.atwoz.member.application.info.dto.profile.position;

import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;

public record PositionWriteRequest(
        @NotNull(message = "위도가 있어야 합니다.")
        BigDecimal latitude,

        @NotNull(message = "경도가 있어야 합니다.")
        BigDecimal longitude
) {
}
