package com.atwoz.member.application.event;

import com.atwoz.global.event.Event;
import com.atwoz.member.application.info.dto.StyleWriteRequest;
import lombok.Getter;
import java.util.List;

@Getter
public class StyleWroteEvent extends Event {

    private final Long memberId;
    private final List<StyleWriteRequest> styles;

    public StyleWroteEvent(final Long memberId, final List<StyleWriteRequest> styles) {
        super();
        this.memberId = memberId;
        this.styles = styles;
    }
}
