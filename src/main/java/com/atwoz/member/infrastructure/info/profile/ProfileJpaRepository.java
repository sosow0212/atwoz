package com.atwoz.member.infrastructure.info.profile;

import com.atwoz.member.domain.info.profile.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface ProfileJpaRepository extends JpaRepository<Profile, Long> {

    Profile save(final Profile profile);

    Optional<Profile> findByMemberId(final Long memberId);
    
    boolean existsByMemberId(final Long memberId);
}
