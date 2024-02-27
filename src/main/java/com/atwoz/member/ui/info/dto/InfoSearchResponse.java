package com.atwoz.member.ui.info.dto;

import com.atwoz.member.ui.info.dto.option.OptionSearchResponse;
import com.atwoz.member.ui.info.dto.profile.ProfileSearchResponse;
import java.util.List;

public record InfoSearchResponse(
    ProfileSearchResponse profile,
    OptionSearchResponse option,
    List<HobbySearchResponse> hobbies,
    List<StyleSearchResponse> styles
) {
}
