package com.atwoz.member.application.info.dto.profile;

import jakarta.validation.constraints.NotBlank;

public record LocationWriteRequest(
        @NotBlank(message = "시/도가 작성되어야 합니다.")
        String city,

        @NotBlank(message = "구가 작성되어야 합니다.")
        String sector
) {
}
