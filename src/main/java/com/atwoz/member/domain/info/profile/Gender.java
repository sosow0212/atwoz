package com.atwoz.member.domain.info.profile;

import com.atwoz.member.exception.exceptions.info.profile.body.InvalidGenderException;
import lombok.Getter;
import java.util.Arrays;

@Getter
public enum Gender {

    MALE("남성"),
    FEMALE("여성");

    private final String name;

    Gender(final String name) {
        this.name = name;
    }

    public static Gender findByName(final String name) {
        return Arrays.stream(values())
                .filter(gender -> name.equals(gender.getName()))
                .findFirst()
                .orElseThrow(InvalidGenderException::new);
    }
}
