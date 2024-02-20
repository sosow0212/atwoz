package com.atwoz.member.infrastructure.info.profile;

import com.atwoz.member.domain.info.profile.Profile;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class ProfileRepositoryImpl implements ProfileRepository{

    private final ProfileJpaRepository profileJpaRepository;

    @Override
    public void save(final Profile profile) {
        profileJpaRepository.save(profile);
    }
}
