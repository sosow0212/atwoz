package com.atwoz.member.application.info.dto.option;

import jakarta.validation.constraints.NotBlank;

public record OptionWriteRequest(

        @NotBlank(message = "음주가 작성되어야 합니다.")
        String drink,

        @NotBlank(message = "최종학력이 작성되어야 합니다.")
        String graduate,

        @NotBlank(message = "종교가 작성되어야 합니다.")
        String religion,

        @NotBlank(message = "흡연이 작성되어야 합니다.")
        String smoke,

        @NotBlank(message = "MBTI가 작성되어야 합니다.")
        String mbti
) {
}
