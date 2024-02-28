package com.atwoz.member.infrastructure.info.style;

import com.atwoz.member.domain.info.style.MemberStyle;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface MemberStyleJpaRepository extends JpaRepository<MemberStyle, Long> {

    void deleteByMemberId(final Long memberId);
    List<MemberStyle> findAllByMemberId(Long memberId);
}
