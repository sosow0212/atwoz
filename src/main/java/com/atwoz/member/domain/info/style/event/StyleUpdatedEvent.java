package com.atwoz.member.domain.info.style.event;

import com.atwoz.global.event.Event;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import java.util.List;

@Getter
@RequiredArgsConstructor
public class StyleUpdatedEvent extends Event {

    private final Long memberId;
    private final List<String> styleCodes;
}
