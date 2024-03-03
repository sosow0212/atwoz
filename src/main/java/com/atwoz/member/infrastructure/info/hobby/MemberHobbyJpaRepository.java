package com.atwoz.member.infrastructure.info.hobby;

import com.atwoz.member.domain.info.hobby.MemberHobby;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface MemberHobbyJpaRepository extends JpaRepository<MemberHobby, Long> {

    MemberHobby save(final MemberHobby memberHobby);

    void saveAll(final List<MemberHobby> memberHobbies);

    @Modifying
    @Query("delete from MemberHobby mh where mh.memberId = :memberId")
    void deleteByMemberId(final Long memberId);

    List<MemberHobby> findAllByMemberId(final Long memberId);
}
