package com.atwoz.member.infrastructure.info.profile.position.dto;

import com.atwoz.member.domain.info.profile.position.Position;
import java.math.BigDecimal;

public record PositionSearchResponse(
        BigDecimal latitude,
        BigDecimal longitude
) {

    public static PositionSearchResponse from(final Position position) {
        return new PositionSearchResponse(position.getLatitude(), position.getLongitude());
    }
}
