package com.atwoz.member.application.event.info;

import com.atwoz.global.event.Event;
import com.atwoz.member.application.info.dto.profile.ProfileUpdateRequest;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ProfileUpdatedEvent extends Event {

    private final Long memberId;
    private final ProfileUpdateRequest request;
}
