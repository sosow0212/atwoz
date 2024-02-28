package com.atwoz.member.application.info.dto.profile;

import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;

public record PositionUpdateRequest(
        @NotNull(message = "위도가 있어야 합니다.")
        BigDecimal latitude,

        @NotNull(message = "경도가 있어야 합니다.")
        BigDecimal longitude
) {
}
