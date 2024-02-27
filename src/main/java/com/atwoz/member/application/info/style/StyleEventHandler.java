package com.atwoz.member.application.info.style;

import com.atwoz.member.application.event.StyleWroteEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import java.util.List;

@RequiredArgsConstructor
@Component
public class StyleEventHandler {

    private final StyleService styleService;

    @EventListener
    public void writeHobbies(final StyleWroteEvent event) {
        Long memberId = event.getMemberId();
        List<String> styleCodes = event.getStyleCodes();

        styleService.saveMemberStyles(memberId, styleCodes);
    }
}
