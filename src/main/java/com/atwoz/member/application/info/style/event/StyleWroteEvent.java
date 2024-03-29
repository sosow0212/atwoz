package com.atwoz.member.application.info.style.event;

import com.atwoz.global.event.Event;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import java.util.List;

@Getter
@RequiredArgsConstructor
public class StyleWroteEvent extends Event {

    private final Long memberId;
    private final List<String> styleCodes;
}
