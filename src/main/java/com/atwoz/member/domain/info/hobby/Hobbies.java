package com.atwoz.member.domain.info.hobby;

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
public class Hobbies {

    private static final int MINIMUM_SIZE = 1;
    private static final int MAXIMUM_SIZE = 3;

    @Column(name = "member_id", insertable = false, updatable = false)
    private Long memberId;

    @ElementCollection
    @CollectionTable(name = "member_hobbies", joinColumns = @JoinColumn(name = "member_id"))
    private List<Hobby> hobbies;

    public Hobbies(final Long memberId, final List<String> hobbies) {
        validateHobbies(hobbies);
        List<Hobby> newHobbies = hobbies.stream()
                .map(Hobby::from)
                .toList();

        this.memberId = memberId;
        this.hobbies = newHobbies;
    }

    private void validateHobbies(final List<String> hobbies) {
        validateIsDuplicate(hobbies);
        validateSize(hobbies);
    }

    private void validateIsDuplicate(final List<String> hobbies) {
        Set<String> hobbySet = new HashSet<>(hobbies);
        if (hobbies.size() != hobbySet.size()) {
            throw new IllegalArgumentException();
        }
    }

    private void validateSize(final List<String> hobbies) {
        if (hobbies.isEmpty() || hobbies.size() > MAXIMUM_SIZE) {
            throw new IllegalArgumentException();
        }
    }
}
