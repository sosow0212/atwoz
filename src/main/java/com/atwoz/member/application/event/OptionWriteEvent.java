package com.atwoz.member.application.event;

import com.atwoz.global.event.Event;
import com.atwoz.member.application.info.dto.option.OptionWriteRequest;
import lombok.Getter;

@Getter
public class OptionWriteEvent extends Event {

    private final Long memberId;
    private final OptionWriteRequest request;

    public OptionWriteEvent(final Long memberId, final OptionWriteRequest request) {
        super();
        this.memberId = memberId;
        this.request = request;
    }
}
