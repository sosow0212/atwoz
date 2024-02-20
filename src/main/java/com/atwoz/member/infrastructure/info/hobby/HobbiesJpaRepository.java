package com.atwoz.member.infrastructure.info.hobby;

import com.atwoz.member.domain.info.hobby.Hobbies;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HobbiesJpaRepository extends JpaRepository<Hobbies, Long> {
}
