package com.atwoz.member.application.info.dto.profile;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ProfileUpdateRequest(
        @NotNull(message = "출생년도가 작성되어야 합니다.")
        Integer birthYear,

        @NotNull(message = "키가 작성되어야 합니다.")
        Integer height,

        @NotBlank(message = "성별이 작성되어야 합니다.")
        String gender,

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
