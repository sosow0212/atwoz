package com.atwoz.member.application.info.profile;

import com.atwoz.member.application.info.dto.profile.ProfileWriteRequest;
import com.atwoz.member.domain.info.profile.Profile;
import com.atwoz.member.infrastructure.info.profile.ProfileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class ProfileService {

    private final ProfileRepository profileRepository;
    private final ProfileFactory profileFactory;

    @Transactional
    public void writeProfile(final Long memberId, final ProfileWriteRequest request) {

        Profile profile = profileFactory.fromRequest(memberId, request);
        profileRepository.save(profile);
    }
}
