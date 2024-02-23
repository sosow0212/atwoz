package com.atwoz.member.infrastructure.info.option;

import com.atwoz.member.domain.info.option.Option;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.Optional;

public interface OptionJpaRepository extends JpaRepository<Option, Long> {

    Optional<Option> findByMemberId(Long memberId);

    @Query("select count(*) > 0 from Option o where o.memberId = :memberId")
    boolean isExistMemberOption(final Long memberId);
}
