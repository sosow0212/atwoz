package com.atwoz.member.domain.info.style;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Embeddable
public class Styles {

    private static final int MINIMUM_SIZE = 1;
    private static final int MAXIMUM_SIZE = 3;

    @Column(name = "member_id", insertable = false, updatable = false)
    private Long memberId;

    @ElementCollection
    @CollectionTable(name = "member_styles", joinColumns = @JoinColumn(name = "member_id"))
    private List<Style> styles;

    public Styles(final Long memberId, final List<String> styles) {
        validateStyles(styles);
        List<Style> newStyles = styles.stream()
                .map(Style::from)
                .toList();

        this.memberId = memberId;
        this.styles = newStyles;
    }

    private void validateStyles(final List<String> styles) {
        validateIsDuplicate(styles);
        validateSize(styles);
    }

    private void validateIsDuplicate(final List<String> styles) {
        Set<String> styleSet = new HashSet<>(styles);
        if (styles.size() != styleSet.size()) {
            throw new IllegalArgumentException();
        }
    }

    private void validateSize(final List<String> styles) {
        if (styles.isEmpty() || styles.size() > MAXIMUM_SIZE) {
            throw new IllegalArgumentException();
        }
    }
}
