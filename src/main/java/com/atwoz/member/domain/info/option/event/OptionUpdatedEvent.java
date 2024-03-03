package com.atwoz.member.domain.info.option.event;

import com.atwoz.global.event.Event;
import com.atwoz.member.application.info.option.dto.OptionUpdateRequest;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class OptionUpdatedEvent extends Event {

    private final Long memberId;
    private final OptionUpdateRequest request;
}
