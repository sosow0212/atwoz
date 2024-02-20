package com.atwoz.member.domain.info.image;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import java.util.ArrayList;
import java.util.List;

@Getter
@Embeddable
public class Images {

    @ElementCollection
    private List<Image> images;

    public Images() {
        this.images = new ArrayList<>();
    }
}
