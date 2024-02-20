package com.atwoz.member.application.event;

import com.atwoz.global.event.Event;
import com.atwoz.member.application.info.dto.option.OptionWriteRequest;
import lombok.Getter;

@Getter
public class OptionWroteEvent extends Event {

    private final Long memberId;
    private final OptionWriteRequest request;

    public OptionWroteEvent(final Long memberId, final OptionWriteRequest request) {
        super();
        this.memberId = memberId;
        this.request = request;
    }
}
