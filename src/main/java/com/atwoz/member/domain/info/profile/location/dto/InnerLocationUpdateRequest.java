package com.atwoz.member.domain.info.profile.location.dto;

import com.atwoz.member.application.info.profile.location.dto.LocationUpdateRequest;

public record InnerLocationUpdateRequest(
        String city,
        String sector
) {

    public static InnerLocationUpdateRequest from(final LocationUpdateRequest request) {
        return new InnerLocationUpdateRequest(request.city(), request.sector());
    }
}
