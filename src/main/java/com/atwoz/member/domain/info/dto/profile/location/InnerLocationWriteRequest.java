package com.atwoz.member.domain.info.dto.profile.location;

import com.atwoz.member.application.info.dto.profile.location.LocationWriteRequest;

public record InnerLocationWriteRequest(
        String city,
        String sector
) {

    public static InnerLocationWriteRequest from(final LocationWriteRequest request) {
        return new InnerLocationWriteRequest(request.city(), request.sector());
    }
}
