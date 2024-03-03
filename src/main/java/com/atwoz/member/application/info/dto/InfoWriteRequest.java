package com.atwoz.member.application.info.dto;

import com.atwoz.member.application.info.dto.hobby.HobbyWriteRequest;
import com.atwoz.member.application.info.dto.option.OptionWriteRequest;
import com.atwoz.member.application.info.dto.profile.ProfileWriteRequest;
import com.atwoz.member.application.info.dto.style.StyleWriteRequest;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import java.util.List;

public record InfoWriteRequest(
        @Valid
        @NotNull(message = "profile 요청이 있어야 합니다.")
        ProfileWriteRequest profile,

        @Valid
        @NotNull(message = "option 요청이 있어야 합니다.")
        OptionWriteRequest option,

        @Valid
        @NotNull(message = "취미 요청이 있어야 합니다.")
        List<HobbyWriteRequest> hobbies,

        @Valid
        @NotNull(message = "스타일 요청이 있어야 합니다.")
        List<StyleWriteRequest> styles
) {
}
