package com.atwoz.member.application.info.dto.option;

import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class OptionUpdateRequest implements OptionRequest {

        @NotBlank(message = "음주가 작성되어야 합니다.")
        private String drink;

        @NotBlank(message = "최종학력이 작성되어야 합니다.")
        private String graduate;

        @NotBlank(message = "종교가 작성되어야 합니다.")
        private String religion;

        @NotBlank(message = "흡연이 작성되어야 합니다.")
        private String smoke;

        @NotBlank(message = "MBTI가 작성되어야 합니다.")
        private String mbti;
}
