package com.atwoz.member.application.info.option;

import com.atwoz.member.application.info.option.event.OptionUpdatedEvent;
import com.atwoz.member.application.info.option.event.OptionWroteEvent;
import com.atwoz.member.application.info.option.dto.OptionUpdateRequest;
import com.atwoz.member.application.info.option.dto.OptionWriteRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class OptionEventHandler {

    private final OptionService optionService;

    @EventListener
    public void writeOption(final OptionWroteEvent event) {
        Long memberId = event.getMemberId();
        OptionWriteRequest request = event.getRequest();

        optionService.writeOption(memberId, request);
    }

    @EventListener
    public void updateOption(final OptionUpdatedEvent event) {
        Long memberId = event.getMemberId();
        OptionUpdateRequest request = event.getRequest();

        optionService.updateOption(memberId, request);
    }
}
