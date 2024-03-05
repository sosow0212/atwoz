package com.atwoz.member.infrastructure.info.profile.location.dto;

import com.atwoz.member.domain.info.profile.location.Location;

public record LocationSearchResponse(
        String city,
        String sector
) {

    public static LocationSearchResponse from(final Location location) {
        return new LocationSearchResponse(location.getCity(), location.getSector());
    }
}
