package com.atwoz.member.application.info.dto;

import jakarta.validation.constraints.NotBlank;

public record StyleWriteRequest(
        @NotBlank(message = "스타일이 작성되어야 합니다.")
        String style
) {
}
