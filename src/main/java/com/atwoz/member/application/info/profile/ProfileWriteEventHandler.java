package com.atwoz.member.application.info.profile;

import com.atwoz.member.application.event.ProfileWriteEvent;
import com.atwoz.member.application.info.dto.profile.ProfileWriteRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class ProfileWriteEventHandler {

    private final ProfileService profileService;

    @EventListener
    public void writeProfile(final ProfileWriteEvent event) {
        Long memberId = event.getMemberId();
        ProfileWriteRequest request = event.getRequest();

        profileService.writeProfile(memberId, request);
    }
}
