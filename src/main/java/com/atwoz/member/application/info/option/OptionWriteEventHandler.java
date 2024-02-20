package com.atwoz.member.application.info.option;

import com.atwoz.member.application.event.OptionWriteEvent;
import com.atwoz.member.application.info.dto.option.OptionWriteRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class OptionWriteEventHandler {

    private final OptionService optionService;

    @EventListener
    public void writeOption(final OptionWriteEvent event) {
        Long memberId = event.getMemberId();
        OptionWriteRequest request = event.getRequest();
        optionService.writeOption(memberId, request);
    }
}
