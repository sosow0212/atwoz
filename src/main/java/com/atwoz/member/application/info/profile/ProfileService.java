package com.atwoz.member.application.info.profile;

import com.atwoz.member.application.info.dto.profile.ProfileUpdateRequest;
import com.atwoz.member.application.info.dto.profile.ProfileWriteRequest;
import com.atwoz.member.domain.info.profile.Profile;
import com.atwoz.member.domain.info.profile.ProfileRepository;
import com.atwoz.member.domain.info.profile.YearManager;
import com.atwoz.member.exception.exceptions.info.profile.ProfileNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class ProfileService {

    private final ProfileRepository profileRepository;
    private final YearManager yearManager;

    @Transactional
    public void writeProfile(final Long memberId, final ProfileWriteRequest request) {
        Profile newProfile = ProfileFactory.createProfile(memberId, request, yearManager);
        if (!profileRepository.isExistMemberProfile(memberId)) {
            profileRepository.save(newProfile);
        }
    }

    private Profile findByMemberId(final Long memberId) {
        return profileRepository.findByMemberId(memberId)
                .orElseThrow(ProfileNotFoundException::new);
    }

    @Transactional
    public void updateProfile(final Long memberId, final ProfileUpdateRequest request) {
        Profile existProfile = findByMemberId(memberId);
        Profile newProfile = ProfileFactory.createProfile(memberId, request, yearManager);

        existProfile.updateContents(
                newProfile.getBody(),
                newProfile.getLocation(),
                newProfile.getPosition(),
                newProfile.getJob()
        );
    }
}
