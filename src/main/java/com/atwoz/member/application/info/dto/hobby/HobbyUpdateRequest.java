package com.atwoz.member.application.info.dto.hobby;

import jakarta.validation.constraints.NotBlank;

public record HobbyUpdateRequest(
        @NotBlank(message = "취미가 작성되어야 합니다.")
        String hobby
) {
}
