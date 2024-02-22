package com.atwoz.member.application.info.profile;

import com.atwoz.member.application.info.dto.profile.ProfileWriteRequest;
import com.atwoz.member.domain.info.profile.Profile;
import com.atwoz.member.domain.info.profile.ProfileRepository;
import com.atwoz.member.exception.exceptions.info.profile.ProfileNotFoundException;
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
        Profile newProfile = profileFactory.fromRequest(memberId, request);

        if (!isMemberProfileExist(memberId)) {
            profileRepository.save(newProfile);
            return;
        }

        Profile existProfile = findByMemberId(memberId);
        existProfile.updateContents(
                newProfile.getBody(),
                newProfile.getLocation(),
                newProfile.getPosition(),
                newProfile.getJob()
        );
    }

    private boolean isMemberProfileExist(final Long memberId) {
        return profileRepository.isExistMemberProfile(memberId);
    }

    public Profile findByMemberId(final Long memberId) {
        return profileRepository.findByMemberId(memberId)
                .orElseThrow(ProfileNotFoundException::new);
    }
}
