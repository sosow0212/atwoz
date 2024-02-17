package com.atwoz.member.domain.profile;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;

@Getter
@Embeddable
public class Location {

    @Column(nullable = false)
    private String city;

    @Column(nullable = false)
    private String sector;
}
