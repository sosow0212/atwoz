package com.atwoz.member.infrastructure.auth.dto;

import jakarta.validation.constraints.NotBlank;

public record MemberInfoResponse(
        @NotBlank(message = "이메일 정보가 비었습니다.")
        String email,

        @NotBlank(message = "닉네임 정보가 비었습니다.")
        String name
) {
}
