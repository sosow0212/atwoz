package com.atwoz.member.infrastructure.info.profile;

import com.atwoz.member.domain.info.profile.Profile;
import java.util.Optional;

public interface ProfileRepository {

    void save(final Profile profile);
    Optional<Profile> findByMemberId(final Long memberId);
}
