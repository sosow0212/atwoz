package com.atwoz.member.application.info.dto;

import com.atwoz.member.application.info.dto.option.OptionWriteRequest;
import com.atwoz.member.application.info.dto.profile.ProfileWriteRequest;
import java.util.List;

public record InfoWriteRequest(
        ProfileWriteRequest profile,

        OptionWriteRequest option,

        List<HobbyWriteRequest> hobbies,

        List<StyleWriteRequest> styles
) {
}
