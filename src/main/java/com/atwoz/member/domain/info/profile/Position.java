package com.atwoz.member.domain.info.profile;

import com.atwoz.member.exception.exceptions.info.profile.ProfileRangeException;
import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@EqualsAndHashCode
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Embeddable
public class Position {

    private static final int MIN_LATITUDE = -90;
    private static final int MAX_LATITUDE = 90;
    private static final int MIN_LONGITUDE = -180;
    private static final int MAX_LONGITUDE = 180;

    private double latitude;
    private double longitude;

    public Position(final double latitude, final double longitude) {
        validateLatitude(latitude);
        validateLongitude(longitude);

        this.latitude = latitude;
        this.longitude = longitude;
    }

    private static void validateLatitude(final double latitude) {
        if (latitude < MIN_LATITUDE || latitude > MAX_LATITUDE) {
            throw new ProfileRangeException();
        }
    }

    private static void validateLongitude(final double longitude) {
        if (longitude < MIN_LONGITUDE || longitude > MAX_LONGITUDE) {
            throw new ProfileRangeException();
        }
    }
}
