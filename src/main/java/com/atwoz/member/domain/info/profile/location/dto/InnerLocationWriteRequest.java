package com.atwoz.member.domain.info.profile.location.dto;

import com.atwoz.member.application.info.profile.location.dto.LocationWriteRequest;

public record InnerLocationWriteRequest(
        String city,
        String sector
) {

    public static InnerLocationWriteRequest from(final LocationWriteRequest request) {
        return new InnerLocationWriteRequest(request.city(), request.sector());
    }
}
