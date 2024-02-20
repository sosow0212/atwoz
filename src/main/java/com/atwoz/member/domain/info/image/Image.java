package com.atwoz.member.domain.info.image;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;

@Getter
@Embeddable
public class Image {

    @Column(nullable = false)
    private String url;
}
