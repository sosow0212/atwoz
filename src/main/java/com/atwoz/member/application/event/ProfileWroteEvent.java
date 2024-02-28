package com.atwoz.member.application.event;

import com.atwoz.global.event.Event;
import com.atwoz.member.application.info.dto.profile.ProfileWriteRequest;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ProfileWroteEvent extends Event {

    private final Long memberId;
    private final ProfileWriteRequest request;
}
