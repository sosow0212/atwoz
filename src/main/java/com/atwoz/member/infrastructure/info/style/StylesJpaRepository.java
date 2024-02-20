package com.atwoz.member.infrastructure.info.style;

import com.atwoz.member.domain.info.style.Styles;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StylesJpaRepository extends JpaRepository<Styles, Long> {
}
