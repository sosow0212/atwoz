package com.atwoz.member.domain.info.profile;

import com.atwoz.member.domain.info.dto.profile.body.InnerBodyUpdateRequest;
import com.atwoz.member.domain.info.dto.profile.body.InnerBodyWriteRequest;
import com.atwoz.member.exception.exceptions.info.profile.body.AgeRangeException;
import com.atwoz.member.exception.exceptions.info.profile.body.HeightRangeException;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@EqualsAndHashCode
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Embeddable
public class Body {

    private static final int MIN_AGE = 19;
    private static final int MAX_AGE = 45;
    private static final int MIN_HEIGHT = 140;
    private static final int MAX_HEIGHT = 199;

    @Column
    private int age;

    @Column
    private int height;

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private Gender gender;

    public Body(final YearManager yearManager, final int birthYear, final int height, final Gender gender) {
        validateHeight(height);

        this.age = calculateAgeFromYear(yearManager, birthYear);
        this.height = height;
        this.gender = gender;
    }

    private void validateHeight(final int height) {
        if (height < MIN_HEIGHT || MAX_HEIGHT < height) {
            throw new HeightRangeException();
        }
    }

    private int calculateAgeFromYear(final YearManager yearManager, final int birthYear) {
        int currentYear = yearManager.getCurrentYear();
        int memberAge = currentYear - birthYear;
        if (memberAge < MIN_AGE || MAX_AGE < memberAge) {
            throw new AgeRangeException();
        }
        return memberAge;
    }

    public static Body createFrom(final InnerBodyWriteRequest request) {
        Gender gender = Gender.findByName(request.gender());
        return new Body(request.yearManager(), request.birthYear(), request.height(), gender);
    }

    public static Body updateContentsFrom(final InnerBodyUpdateRequest request) {
        Gender gender = Gender.findByName(request.gender());
        return new Body(request.yearManager(), request.birthYear(), request.height(), gender);
    }
}
