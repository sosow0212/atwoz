package com.atwoz.member.infrastructure.info.hobby;

import com.atwoz.member.domain.info.hobby.Hobbies;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class HobbiesRepositoryImpl implements HobbiesRepository {

    private final HobbiesJpaRepository hobbiesJpaRepository;

    @Override
    public void save(final Hobbies hobbies) {
        hobbiesJpaRepository.save(hobbies);
    }
}
