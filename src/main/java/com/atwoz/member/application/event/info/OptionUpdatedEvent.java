package com.atwoz.member.application.event.info;

import com.atwoz.global.event.Event;
import com.atwoz.member.application.info.dto.option.OptionUpdateRequest;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class OptionUpdatedEvent extends Event {

    private final Long memberId;
    private final OptionUpdateRequest request;
}
