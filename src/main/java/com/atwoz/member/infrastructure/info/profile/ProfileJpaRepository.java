package com.atwoz.member.infrastructure.info.profile;

import com.atwoz.member.domain.info.profile.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface ProfileJpaRepository extends JpaRepository<Profile, Long> {

    Optional<Profile> findByMemberId(final Long memberId);
}
