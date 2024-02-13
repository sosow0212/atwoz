package com.atwoz.member.domain.profile.image;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import java.util.List;

@Getter
@Embeddable
public class Images {

    @ElementCollection
    private List<Image> images;
}
