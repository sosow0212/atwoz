package com.atwoz.member.domain.profile.style;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import java.util.ArrayList;
import java.util.List;

@Getter
@Embeddable
public class Styles {

    @ElementCollection
    private List<Style> styles;

    public Styles() {
        this.styles = new ArrayList<>();
    }
}
