package com.atwoz.member.application.info.profile.event;

import com.atwoz.global.event.Event;
import com.atwoz.member.application.info.profile.dto.ProfileUpdateRequest;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ProfileUpdatedEvent extends Event {

    private final Long memberId;
    private final ProfileUpdateRequest request;
}
