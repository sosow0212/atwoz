package com.atwoz.member.application.info.hobby;

import com.atwoz.member.domain.info.hobby.event.HobbyUpdatedEvent;
import com.atwoz.member.domain.info.hobby.event.HobbyWroteEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import java.util.List;

@RequiredArgsConstructor
@Component
public class HobbyEventHandler {

    private final MemberHobbyService memberHobbyService;

    @EventListener
    public void writeHobbies(final HobbyWroteEvent event) {
        Long memberId = event.getMemberId();
        List<String> hobbyCodes = event.getHobbyCodes();

        memberHobbyService.saveMemberHobbies(memberId, hobbyCodes);
    }

    @EventListener
    public void updateHobbies(final HobbyUpdatedEvent event) {
        Long memberId = event.getMemberId();
        List<String> hobbyCodes = event.getHobbyCodes();

        memberHobbyService.updateMemberHobbies(memberId, hobbyCodes);
    }
}
