package com.atwoz.member.application.info.style;

import com.atwoz.member.domain.info.style.event.StyleUpdatedEvent;
import com.atwoz.member.domain.info.style.event.StyleWroteEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import java.util.List;

@RequiredArgsConstructor
@Component
public class StyleEventHandler {

    private final MemberStyleService memberStyleService;

    @EventListener
    public void writeHobbies(final StyleWroteEvent event) {
        Long memberId = event.getMemberId();
        List<String> styleCodes = event.getStyleCodes();

        memberStyleService.saveMemberStyles(memberId, styleCodes);
    }

    @EventListener
    public void updateHobbies(final StyleUpdatedEvent event) {
        Long memberId = event.getMemberId();
        List<String> styleCodes = event.getStyleCodes();

        memberStyleService.updateMemberStyles(memberId, styleCodes);
    }
}
