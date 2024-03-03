package com.atwoz.member.domain.info.dto.profile.position;

import com.atwoz.member.application.info.dto.profile.position.PositionUpdateRequest;
import java.math.BigDecimal;

public record InnerPositionUpdateRequest(
        BigDecimal latitude,
        BigDecimal longitude
) {

    public static InnerPositionUpdateRequest from(final PositionUpdateRequest request) {
        return new InnerPositionUpdateRequest(request.latitude(), request.longitude());
    }
}
