package com.atwoz.member.application.info.dto;

import com.atwoz.member.application.info.dto.hobby.HobbyUpdateRequest;
import com.atwoz.member.application.info.dto.option.OptionUpdateRequest;
import com.atwoz.member.application.info.dto.profile.ProfileUpdateRequest;
import com.atwoz.member.application.info.dto.style.StyleUpdateRequest;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import java.util.List;

public record InfoUpdateRequest(
        @Valid
        @NotNull(message = "profile 요청이 있어야 합니다.")
        ProfileUpdateRequest profile,

        @Valid
        @NotNull(message = "option 요청이 있어야 합니다.")
        OptionUpdateRequest option,

        @Valid
        @NotNull(message = "취미 요청이 있어야 합니다.")
        List<HobbyUpdateRequest> hobbies,

        @Valid
        @NotNull(message = "스타일 요청이 있어야 합니다.")
        List<StyleUpdateRequest> styles
) {
}
