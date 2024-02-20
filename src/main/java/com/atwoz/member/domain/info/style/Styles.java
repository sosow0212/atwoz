package com.atwoz.member.domain.info.style;

import com.atwoz.global.exception.exceptions.NullValueException;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Styles {

    private static final int MINIMUM_SIZE = 1;
    private static final int MAXIMUM_SIZE = 3;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long memberId;

    @ElementCollection
    private List<Style> styles;

    public Styles(final Long memberId, final List<String> styles) {
        validateIsNullMemberId(memberId);
        validateStyles(styles);
        List<Style> newStyles = styles.stream()
                .map(Style::from)
                .toList();
        
        this.memberId = memberId;
        this.styles = newStyles;
    }

    private void validateIsNullMemberId(final Long memberId) {
        if (memberId == null) {
            throw new NullValueException();
        }
    }

    private void validateStyles(final List<String> styles) {
        validateIsNullStyles(styles);
        validateIsDuplicate(styles);
        validateSize(styles);
    }

    private void validateIsNullStyles(final List<String> styles) {
        if (styles == null) {
            throw new NullValueException();
        }
    }

    private void validateIsDuplicate(final List<String> styles) {
        Set<String> styleSet = new HashSet<>(styles);
        if (styles.size() != styleSet.size()) {
            throw new IllegalArgumentException();
        }
    }

    private void validateSize(final List<String> styles) {
        if (styles.size() < MINIMUM_SIZE || styles.size() > MAXIMUM_SIZE) {
            throw new IllegalArgumentException();
        }
    }
}
