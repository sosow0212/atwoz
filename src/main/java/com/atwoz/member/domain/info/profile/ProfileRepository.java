package com.atwoz.member.domain.info.profile;

import java.util.Optional;

public interface ProfileRepository {

    void save(final Profile profile);
    Optional<Profile> findByMemberId(final Long memberId);
    boolean isExistMemberProfile(final Long memberId);
}
