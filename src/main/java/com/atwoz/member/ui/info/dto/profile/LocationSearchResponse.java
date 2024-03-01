package com.atwoz.member.ui.info.dto.profile;

import com.atwoz.member.domain.info.profile.Location;

public record LocationSearchResponse(
        String city,
        String sector
) {

    public static LocationSearchResponse from(final Location location) {
        return new LocationSearchResponse(location.getCity(), location.getSector());
    }
}
