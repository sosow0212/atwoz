package com.atwoz.member.infrastructure.info.style;

import com.atwoz.member.domain.info.style.Style;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface StyleJpaRepository extends JpaRepository<Style, Long> {

    void deleteByMemberId(final Long memberId);
    List<Style> findAllByMemberId(Long memberId);
}
