package com.atwoz.member.infrastructure.info.style;

import com.atwoz.member.domain.info.style.MemberStyle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface MemberStyleJpaRepository extends JpaRepository<MemberStyle, Long> {

    @Modifying
    @Query("delete from MemberStyle ms where ms.memberId = :memberId")
    void deleteByMemberId(final Long memberId);

    List<MemberStyle> findAllByMemberId(final Long memberId);
}
