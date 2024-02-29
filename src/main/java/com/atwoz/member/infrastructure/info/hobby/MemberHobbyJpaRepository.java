package com.atwoz.member.infrastructure.info.hobby;

import com.atwoz.member.domain.info.hobby.MemberHobby;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface MemberHobbyJpaRepository extends JpaRepository<MemberHobby, Long> {

    void deleteByMemberId(final Long memberId);

    List<MemberHobby> findAllByMemberId(final Long memberId);
}
