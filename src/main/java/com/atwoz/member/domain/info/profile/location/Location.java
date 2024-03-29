package com.atwoz.member.domain.info.profile.location;

import com.atwoz.member.domain.info.profile.location.dto.InnerLocationUpdateRequest;
import com.atwoz.member.domain.info.profile.location.dto.InnerLocationWriteRequest;
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

    public static Location createFrom(final InnerLocationWriteRequest request) {
        return new Location(request.city(), request.sector());
    }

    public static Location updateContentsFrom(final InnerLocationUpdateRequest request) {
        return new Location(request.city(), request.sector());
    }
}
