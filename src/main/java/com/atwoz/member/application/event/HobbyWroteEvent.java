package com.atwoz.member.application.event;

import com.atwoz.global.event.Event;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import java.util.List;

@Getter
@RequiredArgsConstructor
public class HobbyWroteEvent extends Event {

    private final Long memberId;
    private final List<String> hobbyCodes;
}
