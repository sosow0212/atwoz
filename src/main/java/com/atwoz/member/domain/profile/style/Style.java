package com.atwoz.member.domain.profile.style;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;

@Getter
@Embeddable
public class Style {

    @Column(nullable = false)
    private String style;
}
