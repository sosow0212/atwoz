package com.atwoz.member.infrastructure.info.profile;

import com.atwoz.member.domain.info.profile.Profile;

public interface ProfileRepository {

    void save(final Profile profile);
}
