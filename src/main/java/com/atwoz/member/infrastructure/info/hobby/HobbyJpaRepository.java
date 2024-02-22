package com.atwoz.member.infrastructure.info.hobby;

import com.atwoz.member.domain.info.hobby.Hobby;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface HobbyJpaRepository extends JpaRepository<Hobby, Long> {

    void deleteByMemberId(final Long memberId);

    List<Hobby> findAllByMemberId(Long memberId);
}
