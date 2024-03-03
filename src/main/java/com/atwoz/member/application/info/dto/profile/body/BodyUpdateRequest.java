package com.atwoz.member.application.info.dto.profile.body;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record BodyUpdateRequest(
        @NotNull(message = "출생년도가 작성되어야 합니다.")
        Integer birthYear,

        @NotNull(message = "키가 작성되어야 합니다.")
        Integer height,

        @NotBlank(message = "성별이 작성되어야 합니다.")
        String gender
) {
}
