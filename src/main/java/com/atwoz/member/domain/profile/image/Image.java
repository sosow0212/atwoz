package com.atwoz.member.domain.profile.image;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;

@Getter
@Embeddable
public class Image {

    @Column(nullable = false)
    private String url;
}
