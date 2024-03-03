package com.atwoz.member.application.info.profile;

import com.atwoz.member.domain.info.profile.event.ProfileUpdatedEvent;
import com.atwoz.member.domain.info.profile.event.ProfileWroteEvent;
import com.atwoz.member.application.info.profile.dto.ProfileUpdateRequest;
import com.atwoz.member.application.info.profile.dto.ProfileWriteRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class ProfileEventHandler {

    private final ProfileService profileService;

    @EventListener
    public void writeProfile(final ProfileWroteEvent event) {
        Long memberId = event.getMemberId();
        ProfileWriteRequest request = event.getRequest();

        profileService.writeProfile(memberId, request);
    }

    @EventListener
    public void updateProfile(final ProfileUpdatedEvent event) {
        Long memberId = event.getMemberId();
        ProfileUpdateRequest request = event.getRequest();

        profileService.updateProfile(memberId, request);
    }
}
