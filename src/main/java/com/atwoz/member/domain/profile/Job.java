package com.atwoz.member.domain.profile;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;

@Getter
@Embeddable
public class Job {

    @Column(nullable = false)
    private String job;
}
