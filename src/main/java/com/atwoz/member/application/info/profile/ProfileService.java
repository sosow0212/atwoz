package com.atwoz.member.application.info.profile;

import com.atwoz.member.application.info.dto.profile.ProfileWriteRequest;
import com.atwoz.member.domain.info.profile.Profile;
import com.atwoz.member.infrastructure.info.profile.ProfileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class ProfileService {

    private final ProfileRepository profileRepository;
    private final ProfileFactory profileFactory;

    @Transactional
    public void writeProfile(final Long memberId, final ProfileWriteRequest request) {

        Optional<Profile> findProfile = findByMemberId(memberId);
        Profile newProfile = profileFactory.fromRequest(memberId, request);
        if (findProfile.isEmpty()) {
            profileRepository.save(newProfile);
            return;
        }
        Profile existProfile = findProfile.get();
        existProfile.updateContents(newProfile.getMemberBody(), newProfile.getLocation(), newProfile.getJob());
    }

    private Optional<Profile> findByMemberId(final Long memberId) {
        return profileRepository.findByMemberId(memberId);
    }
}
