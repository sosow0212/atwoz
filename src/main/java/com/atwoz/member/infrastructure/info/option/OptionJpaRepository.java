package com.atwoz.member.infrastructure.info.option;

import com.atwoz.member.domain.info.option.Option;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface OptionJpaRepository extends JpaRepository<Option, Long> {

    Option save(final Option option);

    Optional<Option> findByMemberId(final Long memberId);

    boolean existsByMemberId(final Long memberId);
}
