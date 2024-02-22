package com.atwoz.member.ui.info;

import com.atwoz.member.application.info.profile.ProfileService;
import com.atwoz.member.domain.info.profile.Profile;
import com.atwoz.member.ui.auth.support.auth.AuthMember;
import com.atwoz.member.ui.info.dto.profile.ProfileSearchResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/api/info/profile")
@RestController
public class ProfileController {

    private final ProfileService profileService;

    @GetMapping
    public ResponseEntity<ProfileSearchResponse> findProfile(@AuthMember Long memberId) {
        Profile profile = profileService.findByMemberId(memberId);
        ProfileSearchResponse response = ProfileSearchResponse.from(profile);

        return ResponseEntity.ok()
                .body(response);
    }
}
