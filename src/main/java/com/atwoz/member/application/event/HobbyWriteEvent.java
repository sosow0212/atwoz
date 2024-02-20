package com.atwoz.member.application.event;

import com.atwoz.global.event.Event;
import com.atwoz.member.application.info.dto.HobbyWriteRequest;
import lombok.Getter;
import java.util.List;

@Getter
public class HobbyWriteEvent extends Event {

    private final Long memberId;
    private final List<HobbyWriteRequest> hobbies;

    public HobbyWriteEvent(final Long memberId, final List<HobbyWriteRequest> hobbies) {
        super();
        this.memberId = memberId;
        this.hobbies = hobbies;
    }
}
