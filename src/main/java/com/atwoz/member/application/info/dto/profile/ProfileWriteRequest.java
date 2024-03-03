package com.atwoz.member.application.info.dto.profile;

import com.atwoz.member.application.info.dto.profile.body.BodyWriteRequest;
import com.atwoz.member.application.info.dto.profile.location.LocationWriteRequest;
import com.atwoz.member.application.info.dto.profile.position.PositionWriteRequest;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ProfileWriteRequest(
        @Valid
        @NotNull(message = "신체 정보가 작성되어야 합니다.")
        BodyWriteRequest body,

        @Valid
        @NotNull(message = "위치가 작성되어야 합니다.")
        LocationWriteRequest location,

        @Valid
        @NotNull(message = "위도-경도가 작성되어야 합니다.")
        PositionWriteRequest position,

        @NotBlank(message = "직업이 작성되어야 합니다.")
        String job
) {
}
