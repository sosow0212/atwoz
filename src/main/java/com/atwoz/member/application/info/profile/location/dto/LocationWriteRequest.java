package com.atwoz.member.application.info.profile.location.dto;

import jakarta.validation.constraints.NotBlank;

public record LocationWriteRequest(
        @NotBlank(message = "시/도가 작성되어야 합니다.")
        String city,

        @NotBlank(message = "구/군이 작성되어야 합니다.")
        String sector
) {
}
