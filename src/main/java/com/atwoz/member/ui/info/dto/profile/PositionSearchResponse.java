package com.atwoz.member.ui.info.dto.profile;

import com.atwoz.member.domain.info.profile.Position;

public record PositionSearchResponse(

        double latitude,

        double longitude
) {
    public static PositionSearchResponse from(final Position position) {
        return new PositionSearchResponse(position.getLatitude(), position.getLongitude());
    }
}
