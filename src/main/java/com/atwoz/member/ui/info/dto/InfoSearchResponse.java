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
