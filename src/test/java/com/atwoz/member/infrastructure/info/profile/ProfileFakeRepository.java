package com.atwoz.member.infrastructure.info.profile;

import com.atwoz.member.domain.info.profile.Profile;
import com.atwoz.member.domain.info.profile.ProfileRepository;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class ProfileFakeRepository implements ProfileRepository {

    private final Map<Long, Profile> map = new HashMap<>();
    private long id = 1L;

    @Override
    public void save(final Profile profile) {
        Profile newProfile = new Profile(
                profile.getMemberId(),
                profile.getBody(),
                profile.getLocation(),
                profile.getPosition(),
                profile.getJob()
        );

        map.put(id, newProfile);
        id++;
    }

    @Override
    public Optional<Profile> findByMemberId(final Long memberId) {
        return Optional.ofNullable(map.get(memberId));
    }

    @Override
    public boolean isExistMemberProfile(final Long memberId) {
        return findByMemberId(memberId).isPresent();
    }
}
