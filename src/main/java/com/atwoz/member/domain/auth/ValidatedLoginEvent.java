package com.atwoz.member.domain.auth;

import com.atwoz.global.event.Event;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ValidatedLoginEvent extends Event {

    private final String email;
    private final String nickname;
}