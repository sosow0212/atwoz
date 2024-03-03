package com.atwoz.member.domain.info.dto.profile.location;

import com.atwoz.member.application.info.dto.profile.location.LocationUpdateRequest;

public record InnerLocationUpdateRequest(
        String city,
        String sector
) {

    public static InnerLocationUpdateRequest from(final LocationUpdateRequest request) {
        return new InnerLocationUpdateRequest(request.city(), request.sector());
    }
}
