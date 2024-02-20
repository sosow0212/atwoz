package com.atwoz.member.domain.info.profile;

import com.atwoz.global.exception.exceptions.NullValueException;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Embeddable
public class Location {

    @Column(nullable = false)
    private String city;

    @Column(nullable = false)
    private String sector;

    public Location(final String city, final String sector) {
        validateValues(city, sector);

        this.city = city;
        this.sector = sector;
    }

    private void validateValues(final String city, final String sector) {
        if (city == null || sector == null) {
            throw new NullValueException();
        }
    }
}
