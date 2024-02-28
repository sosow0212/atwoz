package com.atwoz.member.application.info.hobby;

import com.atwoz.member.application.event.info.HobbyUpdatedEvent;
import com.atwoz.member.application.event.info.HobbyWroteEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import java.util.List;

@RequiredArgsConstructor
@Component
public class HobbyEventHandler {

    private final HobbyService hobbyService;

    @EventListener
    public void writeHobbies(final HobbyWroteEvent event) {
        Long memberId = event.getMemberId();
        List<String> hobbyCodes = event.getHobbyCodes();

        hobbyService.saveMemberHobbies(memberId, hobbyCodes);
    }

    @EventListener
    public void updateHobbies(final HobbyUpdatedEvent event) {
        Long memberId = event.getMemberId();
        List<String> hobbyCodes = event.getHobbyCodes();
    }
}
