package com.atwoz.member.domain.info.profile;

import com.atwoz.member.exception.exceptions.info.profile.ProfileRangeException;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Embeddable
public class Body {

    private static final int MIN_AGE = 20;
    private static final int MAX_AGE = 45;
    private static final int MIN_HEIGHT = 140;
    private static final int MAX_HEIGHT = 199;

    @Column
    private int birthYear;

    @Column
    private int height;

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private Gender gender;

    public Body(final int birthYear, final int height, final Gender gender) {
        validateValues(birthYear, height);

        this.birthYear = birthYear;
        this.height = height;
        this.gender = gender;
    }

    public void validateValues(final int birthYear, final int height) {
        validateAgeFromBirthYear(birthYear);
        validateHeight(height);
    }

    private void validateAgeFromBirthYear(final int age) {
        LocalDateTime nowTime = LocalDateTime.now();
        int nowYear = nowTime.getYear();
        int memberAge = Math.abs(nowYear - age);
        if (memberAge < MIN_AGE|| MAX_AGE < memberAge) {
            throw new ProfileRangeException();
        }
    }

    private void validateHeight(final int height) {
        if (height < MIN_HEIGHT || MAX_HEIGHT < height) {
            throw new ProfileRangeException();
        }
    }
}