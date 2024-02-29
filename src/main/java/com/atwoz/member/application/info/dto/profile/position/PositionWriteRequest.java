package com.atwoz.member.application.info.dto.profile.position;

import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class PositionWriteRequest implements PositionRequest {

        @NotNull(message = "위도가 있어야 합니다.")
        private BigDecimal latitude;

        @NotNull(message = "경도가 있어야 합니다.")
        private BigDecimal longitude;
}
