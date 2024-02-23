package com.atwoz.member.infrastructure.info.profile;

import com.atwoz.member.domain.info.profile.Profile;
import com.atwoz.member.domain.info.profile.ProfileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class ProfileRepositoryImpl implements ProfileRepository {

    private final ProfileJpaRepository profileJpaRepository;

    @Override
    public void save(final Profile profile) {
        profileJpaRepository.save(profile);
    }

    @Override
    public Optional<Profile> findByMemberId(final Long memberId) {
        return profileJpaRepository.findByMemberId(memberId);
    }

    @Override
    public boolean isExistMemberProfile(final Long memberId) {
        return profileJpaRepository.isExistMemberOption(memberId);
    }
}
