package com.atwoz.member.application.event;

import com.atwoz.global.event.Event;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ValidatedLoginEvent extends Event {

    private final String email;
    private final String nickname;
}
