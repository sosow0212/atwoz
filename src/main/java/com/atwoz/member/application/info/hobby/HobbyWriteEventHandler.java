package com.atwoz.member.application.info.hobby;

import com.atwoz.member.application.event.HobbyWriteEvent;
import com.atwoz.member.application.info.dto.HobbyWriteRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import java.util.List;

@RequiredArgsConstructor
@Component
public class HobbyWriteEventHandler {

    private final HobbyService hobbyService;

    @EventListener
    public void writeHobbies(final HobbyWriteEvent event) {
        List<String> hobbies = event.getHobbies()
                .stream()
                .map(HobbyWriteRequest::hobby)
                .toList();

        hobbyService.writeHobbies(event.getMemberId(), hobbies);
    }
}
