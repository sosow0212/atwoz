package com.atwoz.member.domain.info.profile.position.dto;

import com.atwoz.member.application.info.profile.position.dto.PositionUpdateRequest;
import java.math.BigDecimal;

public record InnerPositionUpdateRequest(
        BigDecimal latitude,
        BigDecimal longitude
) {

    public static InnerPositionUpdateRequest from(final PositionUpdateRequest request) {
        return new InnerPositionUpdateRequest(request.latitude(), request.longitude());
    }
}
