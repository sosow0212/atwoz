package com.atwoz.member.domain.info.hobby;

import com.atwoz.global.exception.exceptions.NullValueException;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Embeddable
public class Hobby {

    @Column(nullable = false)
    private String hobby;

    public static Hobby from(final String hobby) {
        validateIsNotNull(hobby);
        return new Hobby(hobby);
    }

    private static void validateIsNotNull(final String hobby) {
        if (hobby == null) {
            throw new NullValueException();
        }
    }
}
