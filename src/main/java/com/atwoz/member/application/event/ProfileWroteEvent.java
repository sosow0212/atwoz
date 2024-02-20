package com.atwoz.member.application.event;

import com.atwoz.global.event.Event;
import com.atwoz.member.application.info.dto.profile.ProfileWriteRequest;
import lombok.Getter;

@Getter
public class ProfileWroteEvent extends Event {

    private final Long memberId;
    private final ProfileWriteRequest request;

    public ProfileWroteEvent(final Long memberId, final ProfileWriteRequest request) {
        super();
        this.memberId = memberId;
        this.request = request;
    }
}
