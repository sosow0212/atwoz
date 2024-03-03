package com.atwoz.member.application.info.profile;

import com.atwoz.member.application.info.profile.dto.ProfileUpdateRequest;
import com.atwoz.member.application.info.profile.dto.ProfileWriteRequest;
import com.atwoz.member.application.info.profile.dto.InnerProfileUpdateRequest;
import com.atwoz.member.domain.info.profile.Profile;
import com.atwoz.member.domain.info.profile.ProfileRepository;
import com.atwoz.member.domain.info.profile.body.YearManager;
import com.atwoz.member.application.info.profile.dto.InnerProfileWriteRequest;
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
        InnerProfileWriteRequest profileWriteRequest = InnerProfileWriteRequest.of(memberId, yearManager, request);
        Profile newProfile = Profile.createFrom(profileWriteRequest);
        if (!profileRepository.isExistMemberProfile(memberId)) {
            profileRepository.save(newProfile);
        }
    }

    @Transactional
    public void updateProfile(final Long memberId, final ProfileUpdateRequest request) {
        InnerProfileUpdateRequest profileUpdateRequest = InnerProfileUpdateRequest.of(memberId, yearManager, request);
        Profile existProfile = findByMemberId(memberId);
        existProfile.updateContentsFrom(profileUpdateRequest);
    }

    private Profile findByMemberId(final Long memberId) {
        return profileRepository.findByMemberId(memberId)
                .orElseThrow(ProfileNotFoundException::new);
    }
}
