package com.atwoz.member.application.info.dto.profile;

import com.atwoz.member.application.info.dto.profile.body.BodyUpdateRequest;
import com.atwoz.member.application.info.dto.profile.location.LocationUpdateRequest;
import com.atwoz.member.application.info.dto.profile.position.PositionUpdateRequest;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ProfileUpdateRequest(
        @Valid
        @NotNull(message = "신체 정보가 작성되어야 합니다.")
        BodyUpdateRequest body,

        @Valid
        @NotNull(message = "위치가 작성되어야 합니다.")
        LocationUpdateRequest location,

        @Valid
        @NotNull(message = "위도-경도가 작성되어야 합니다.")
        PositionUpdateRequest position,

        @NotBlank(message = "직업이 작성되어야 합니다.")
        String job
) {
}
