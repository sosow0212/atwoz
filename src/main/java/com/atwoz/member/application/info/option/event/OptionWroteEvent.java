package com.atwoz.member.application.info.option.event;

import com.atwoz.global.event.Event;
import com.atwoz.member.application.info.option.dto.OptionWriteRequest;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class OptionWroteEvent extends Event {

    private final Long memberId;
    private final OptionWriteRequest request;
}
