package com.atwoz.member.domain.info.profile;

import com.atwoz.global.exception.exceptions.NullValueException;
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
public class MemberBody {

    private static final int MIN_AGE = 20;
    private static final int MAX_AGE = 45;
    private static final int MIN_HEIGHT = 140;
    private static final int MAX_HEIGHT = 199;

    @Column(nullable = false)
    private Integer age;

    @Column(nullable = false)
    private Integer height;

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private Gender gender;

    public MemberBody(final Integer age, final Integer height, final Gender gender) {
        validateValues(age, height);

        this.age = age;
        this.height = height;
        this.gender = gender;
    }

    public void validateValues(final Integer age, final Integer height) {
        validateAge(age);
        validateHeight(height);
    }

    private void validateAge(final Integer age) {
        if (age == null) {
            throw new NullValueException();
        }
        LocalDateTime nowTime = LocalDateTime.now();
        int nowYear = nowTime.getYear();
        int memberAge = Math.abs(nowYear - age);
        if (memberAge < MIN_AGE|| MAX_AGE < memberAge) {
            throw new ProfileRangeException();
        }
    }

    private void validateHeight(final Integer height) {
        if (height == null) {
            throw new NullValueException();
        }
        if (height < MIN_HEIGHT || MAX_HEIGHT < height) {
            throw new ProfileRangeException();
        }
    }
}
