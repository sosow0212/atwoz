package com.atwoz.member.application.event;

import com.atwoz.global.event.Event;
import com.atwoz.member.application.info.dto.StyleWriteRequest;
import lombok.Getter;
import java.util.List;

@Getter
public class StyleWriteEvent extends Event {

    private final Long memberId;
    private final List<StyleWriteRequest> styles;

    public StyleWriteEvent(final Long memberId, final List<StyleWriteRequest> styles) {
        super();
        this.memberId = memberId;
        this.styles = styles;
    }
}
