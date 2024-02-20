package com.atwoz.member.domain.info.style;

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
public class Style {

    @Column(nullable = false)
    private String style;

    public static Style from(final String style) {
        validateIsNotNull(style);
        return new Style(style);
    }

    private static void validateIsNotNull(final String style) {
        if (style == null) {
            throw new NullValueException();
        }
    }
}
