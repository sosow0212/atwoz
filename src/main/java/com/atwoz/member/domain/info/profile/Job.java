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
public class Job {

    @Column(nullable = false)
    private String job;

    public Job(final String job) {
        validateNotNull(job);

        this.job = job;
    }

    public void validateNotNull(final String job) {
        if (job == null) {
            throw new NullValueException();
        }
    }
}
