package com.atwoz.member.infrastructure.info.option;

import com.atwoz.member.domain.info.option.Option;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OptionJpaRepository extends JpaRepository<Option, Long> {
}
