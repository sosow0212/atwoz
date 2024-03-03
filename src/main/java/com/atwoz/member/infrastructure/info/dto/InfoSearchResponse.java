package com.atwoz.member.infrastructure.info.dto;

import com.atwoz.member.infrastructure.info.hobby.dto.HobbySearchResponse;
import com.atwoz.member.infrastructure.info.style.dto.StyleSearchResponse;
import com.atwoz.member.infrastructure.info.option.dto.OptionSearchResponse;
import com.atwoz.member.infrastructure.info.profile.dto.ProfileSearchResponse;
import java.util.List;

public record InfoSearchResponse(
    ProfileSearchResponse profile,
    OptionSearchResponse option,
    List<HobbySearchResponse> hobbies,
    List<StyleSearchResponse> styles
) {

    public static InfoSearchResponse of(
            final ProfileAndOptionSearchResponse profileAndOptionSearchResponse,
            final List<HobbySearchResponse> hobbies,
            final List<StyleSearchResponse> styles
    ) {
        ProfileSearchResponse profile = ProfileSearchResponse.from(profileAndOptionSearchResponse);
        OptionSearchResponse option = OptionSearchResponse.from(profileAndOptionSearchResponse);

        return new InfoSearchResponse(profile, option, hobbies, styles);
    }
}
