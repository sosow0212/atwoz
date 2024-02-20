package com.atwoz.member.infrastructure.info.profile;

import com.atwoz.member.domain.info.profile.Profile;
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
}
