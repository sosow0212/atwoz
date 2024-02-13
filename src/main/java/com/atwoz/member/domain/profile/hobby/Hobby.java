package com.atwoz.member.domain.profile.hobby;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;

@Getter
@Embeddable
public class Hobby {

    @Column(nullable = false)
    private String hobby;
}
