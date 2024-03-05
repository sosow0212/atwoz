package com.atwoz.member.domain.info.profile.position.dto;

import com.atwoz.member.application.info.profile.position.dto.PositionWriteRequest;
import java.math.BigDecimal;

public record InnerPositionWriteRequest(
        BigDecimal latitude,
        BigDecimal longitude
) {

    public static InnerPositionWriteRequest from(final PositionWriteRequest request) {
        return new InnerPositionWriteRequest(request.latitude(), request.longitude());
    }
}
