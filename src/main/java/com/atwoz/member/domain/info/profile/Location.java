package com.atwoz.member.domain.info.profile;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@EqualsAndHashCode
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Embeddable
public class Location {

    @Column(nullable = false)
    private String city;

    @Column(nullable = false)
    private String sector;

    public Location(final String city, final String sector) {
        this.city = city;
        this.sector = sector;
    }
}
