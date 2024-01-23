package com.atwoz.member.application.auth.dto;

import jakarta.validation.constraints.NotBlank;

public record SignupRequest(
        @NotBlank(message = "인증 코드가 비었습니다.")
        String code
) {
}
