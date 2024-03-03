package com.atwoz.member.domain.info.dto.profile.position;

import com.atwoz.member.application.info.dto.profile.position.PositionWriteRequest;
import java.math.BigDecimal;

public record InnerPositionWriteRequest(
        BigDecimal latitude,
        BigDecimal longitude
) {

    public static InnerPositionWriteRequest from(final PositionWriteRequest request) {
        return new InnerPositionWriteRequest(request.latitude(), request.longitude());
    }
}
