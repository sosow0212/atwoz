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
public class Job {

    @Column(nullable = false)
    private String job;

    public Job(final String job) {
        this.job = job;
    }
}
