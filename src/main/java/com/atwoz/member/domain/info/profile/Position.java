package com.atwoz.member.domain.info.profile;

import com.atwoz.member.domain.info.dto.profile.position.InnerPositionUpdateRequest;
import com.atwoz.member.domain.info.dto.profile.position.InnerPositionWriteRequest;
import com.atwoz.member.exception.exceptions.info.profile.position.LatitudeRangeException;
import com.atwoz.member.exception.exceptions.info.profile.position.LongitudeRangeException;
import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;

@Getter
@EqualsAndHashCode
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Embeddable
public class Position {

    private static final BigDecimal MIN_LATITUDE = BigDecimal.valueOf(-90);
    private static final BigDecimal MAX_LATITUDE = BigDecimal.valueOf(90);
    private static final BigDecimal MIN_LONGITUDE = BigDecimal.valueOf(-180);
    private static final BigDecimal MAX_LONGITUDE = BigDecimal.valueOf(180);

    private BigDecimal latitude;
    private BigDecimal longitude;

    public Position(final BigDecimal latitude, final BigDecimal longitude) {
        validateLatitude(latitude);
        validateLongitude(longitude);

        this.latitude = latitude;
        this.longitude = longitude;
    }

    private static void validateLatitude(final BigDecimal latitude) {
        if (latitude.compareTo(MIN_LATITUDE) < 0 || latitude.compareTo(MAX_LATITUDE) > 0) {
            throw new LatitudeRangeException();
        }
    }

    private static void validateLongitude(final BigDecimal longitude) {
        if (longitude.compareTo(MIN_LONGITUDE) < 0 || longitude.compareTo(MAX_LONGITUDE) > 0) {
            throw new LongitudeRangeException();
        }
    }

    public static Position createFrom(final InnerPositionWriteRequest position) {
        return new Position(position.latitude(), position.longitude());
    }

    public static Position updateContentsFrom(final InnerPositionUpdateRequest position) {
        return new Position(position.latitude(), position.longitude());
    }
}
