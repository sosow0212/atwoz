package com.atwoz.member.domain.info.hobby;

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
public class Hobbies {

    private static final int MINIMUM_SIZE = 1;
    private static final int MAXIMUM_SIZE = 3;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long memberId;

    @ElementCollection
    private List<Hobby> hobbies;

    public Hobbies(final Long memberId, final List<String> hobbies) {
        validateIsNullMemberId(memberId);
        validateHobbies(hobbies);
        List<Hobby> newHobbies = hobbies.stream()
                .map(Hobby::from)
                .toList();

        this.hobbies = newHobbies;
    }

    private void validateIsNullMemberId(final Long memberId) {
        if (memberId == null) {
            throw new NullValueException();
        }
    }

    private void validateHobbies(final List<String> hobbies) {
        validateIsNull(hobbies);
        validateIsDuplicate(hobbies);
        validateSize(hobbies);
    }

    private void validateIsNull(final List<String> hobbies) {
        if (hobbies == null) {
            throw new NullValueException();
        }
    }

    private void validateIsDuplicate(final List<String> hobbies) {
        Set<String> hobbySet = new HashSet<>(hobbies);
        if (hobbies.size() != hobbySet.size()) {
            throw new IllegalArgumentException();
        }
    }

    private void validateSize(final List<String> hobbies) {
        if (hobbies.size() < MINIMUM_SIZE || hobbies.size() > MAXIMUM_SIZE) {
            throw new IllegalArgumentException();
        }
    }
}
