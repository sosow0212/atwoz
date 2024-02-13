package com.atwoz.member.domain.profile.hobby;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import java.util.List;

@Getter
@Embeddable
public class Hobbies {

    @ElementCollection
    private List<Hobby> hobbies;
}
